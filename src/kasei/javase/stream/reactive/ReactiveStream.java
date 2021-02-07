package kasei.javase.stream.reactive;

import kasei.OracleJdbc;

import javax.management.RuntimeMBeanException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

/**
 * 响应式流 是一种解决 生产者-消费者模型 的编程模型，也可以说是一种设计模式，跟单例之类的一样
 *
 * Publisher - m:n - Subscription -1:1- Subscriber
 * 
 * 响应式流执行步骤：
 *      Publisher                   Subscription                Subscriber
 *  0   create()                                                                                # Publisher 创建阶段，创建 Publisher 实例
 *  1   subscribe(Subscriber)                                                                   # Publisher 调用该方法签约 Subscriber
 *  2                                                           onSubscribe(Subscription)       # Publisher subscribe() 成功后调用
 *  3                               request(Long)                                               # Subscriber onSubscribe() 成功后调用该方法请求数据
 *  4                                                           onNext(T)                       # Publisher 调用该方法发送数据
 *  5                               cancel()                                                    # Subscriber 不想继续接收数据
 *  6                                                           onError(Throwable)              # Publisher 出错，或者 Subscriber 自身出错，调用该方法
 *  7                                                           onComplete()                    # Publisher 无后续数据发送，调用该方法
 *
 */
public class ReactiveStream {


    /**
     * 什么是 Subscription 订阅？
     *  将 Publisher 和 Subscriber 签订的协议，抽象成为一个独立的类
     */
    static class MySubscription implements Flow.Subscription {

        /** 当前 订阅协议 的所有 发布者 */
        private List<MyPublisher> publisherList = new ArrayList<>();
        /** 当前 订阅协议 的所有 发布者 */
        private List<MySubscriber> subscriberList = new ArrayList<>();
        /** 当前执行订阅协议的 订阅者 */
        private MySubscriber currentSubscriber ; // 怎么做同步控制呢

        public void addPublisher(MyPublisher... publishers) {
            publisherList.addAll(Arrays.asList(publishers));
        }
        public void addSubscriber(MySubscriber... subscribers) {
            subscriberList.addAll(Arrays.asList(subscribers));
        }

        public void setCurrentSubscriber(MySubscriber subscriber){
            this.currentSubscriber = subscriber;
        }
        public MySubscriber getCurrentSubscriber(){return this.currentSubscriber;}
        // subscriber 请求数据
        @Override
        public void request(long n) {
            // 随机选择一个 发布者
            int bound = publisherList.size();
            if (bound == 0) {
                throw new RuntimeException("无 发布者");
            }
            int i = new Random().nextInt(bound);
            MyPublisher publisher = publisherList.get(i);


            // 如何判断是哪个 订阅者
            List<Integer> data = publisher.getData(n);
            System.out.println(this.currentSubscriber.subscriberName + " 开始消费:" + publisher.publisherName + " data = " + data);
            this.currentSubscriber.onNext(data);

        }

        // 取消订阅
        @Override
        public void cancel() {
            subscriberList.remove(this.currentSubscriber);// 移除当前订阅者
            this.currentSubscriber.onComplete(); // 触发订阅者完成事件
        }
    }

    /**
     * 发布者，SubmissionPublisher: JDK 自带的发布者实现
     * 坑：必须跟 Subscriber 是不同的线程
     */
    static class MyPublisher implements Flow.Publisher<List<Integer>> {

        /** 当前 Publisher 持有的 Subscription 订阅协议 */
        private MySubscription subscription ;
        /** 当前 Publisher 的缓冲队列容量 */
        private Integer queueCapacity = 19;
        /** 当前 Publisher 的缓冲队列，其中 ArrayBlockingQueue 是线程安全的 */
        private Queue<Integer> publisherQueue = new ArrayBlockingQueue<>(queueCapacity);
        /** 当前 Publisher 的名称，唯一标识*/
        private String publisherName;

        /** 当前 Publisher 的构造函数，必要参数：订阅协议/发布者名称 */
        public MyPublisher(MySubscription mySubscription, String publisherName) {
            this.subscription = mySubscription;
            this.publisherName = publisherName;
        }



        // 发布者 跟 订阅者 签订协议，所以该方法必定会创建或持有一个 Subscription 协议对象
        // 该方法用于将 订阅者注册到该发布者上，或者叫该发布者签署一个订阅者
        @Override
        public void subscribe(Flow.Subscriber<? super List<Integer>> subscriber) {
            // 判断当前 subscriber 是不是 MySubscriber 的实例
            if (!(subscriber instanceof MySubscriber)) {
                throw new RuntimeException("当前订阅的订阅者，不是指定的订阅者");
            }
            subscription.addSubscriber((MySubscriber) subscriber);
            subscriber.onSubscribe(subscription);
        }

        /**
         * 启动发布者：开始生产数据
         * 由于缓冲区使用的 ArrayBlockingQueue 是线程安全的，所以此处不做加锁处理
         */
        public void startProduce(){
            Random random = new Random();
            new Thread(()->{
                for (int i = 1; ; ) {
                    try {
                        // 一秒钟生产一个数据到 缓冲区
                        TimeUnit.MILLISECONDS.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (publisherQueue.size() < queueCapacity) {
                        System.out.println(publisherName + " 生产： " + i + " 当前缓冲区数据量：" + (publisherQueue.size()+1));
                        publisherQueue.add(i);
                        i++;
                    }
                }
            }, this.publisherName).start();
        }

        /**
         * 从 Publisher 缓冲池中获取指定数量的数据
         * 由于缓冲区使用的 ArrayBlockingQueue 是线程安全的，所以此处不做加锁处理
         * @param subscriberNeedQuantity
         * @return
         */
        public List<Integer> getData(long subscriberNeedQuantity){
            long actualQuantity = subscriberNeedQuantity > publisherQueue.size() ? publisherQueue.size() : subscriberNeedQuantity;
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < actualQuantity; i++) {
                result.add(publisherQueue.remove());
            }
            return result;
        }
    }




    /**
     * 订阅者
     * 一个订阅者必须持有一个独立的订阅协议
     */
    static class MySubscriber implements Flow.Subscriber<List<Integer>> {

        /**
         * 当前 Subscriber 实例持有的 Subscription 协议(合同)
         * Subscriber - 1:1 - Subscription: 一个订阅者能且只能持有一个订阅协议
         */
        private MySubscription subscription;
        /** 当前 Subscriber 的缓冲队列容量 */
        private Integer queueCapacity = 3;
        /** 当前 Subscriber 的缓冲队列 */
        private Queue<Integer> subscriberQueue = new ArrayBlockingQueue<>(queueCapacity);
        /** 当前 Subscriber 的名称，唯一标识*/
        private String subscriberName;

        /** 当前 Subscriber 的构造函数，必要参数：订阅者名称 */
        public MySubscriber(String subscriberName) {
            this.subscriberName = subscriberName;
        }


        /**
         * Publisher 调用 subscribe() 时会调用 Subscriber 的该方法完成协议签订
         * @param subscription 订阅协议实例
         */
        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = (MySubscription) subscription; // 必须保存合同，不然后面其他方法找不到
        }

        /**
         * 在接收到一个数据的时候调用
         * @param item
         */
        @Override
        public void onNext(List<Integer> item) {
            try {
                for (Integer integer : item) {
                    System.out.println(subscriberName + " 消费：" + integer);// 相当于处理逻辑
                    TimeUnit.MILLISECONDS.sleep(134); // 两秒处理一个
                }

            } catch (Throwable throwable) {
                throwable.printStackTrace();
                // 当该方法抛异常时，取消订阅协议
                this.subscription.cancel();
            }
        }

        /**
         * Publisher 和 Subscription 抛异常时调用
         * 坑：该方法本身不能抛异常
         * @param throwable
         */
        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }

        /**
         * 该方法由 Subscription 在：
         *  Subscription 没有因为 error 导致终止且 Subscription 不再调用 Subscriber 的其他方法时调用
         * 坑：该方法本身不能抛异常
         */
        @Override
        public void onComplete() {
            System.out.println("订阅者关闭完成");
        }


        public void startConsume(){
            new Thread(()->{

                while (true) {
                    synchronized (this.subscription) {
                        MySubscriber currentSubscriber = this.subscription.getCurrentSubscriber();
                        // while (currentSubscriber != null && currentSubscriber != this) {
                        //     try {
                        //         this.subscription.wait();
                        //     } catch (InterruptedException e) {
                        //         e.printStackTrace();
                        //     }
                        // }

                        this.subscription.setCurrentSubscriber(this);
                        this.subscription.request(queueCapacity); // 处理完成后再次请求数据
                        this.subscription.setCurrentSubscriber(null);
                    }
                }
            }, this.subscriberName).start();

        }
    }






    public static void main(String[] args) throws InterruptedException {


        // 1. 创建一个协议（订阅）对象
        MySubscription mySubscription = new MySubscription();

        /* 1. 创建一个 发布者 实例 */
        MyPublisher myPublisher1 = new MyPublisher(mySubscription, "P1");
        MyPublisher myPublisher2 = new MyPublisher(mySubscription, "P2");
        mySubscription.addPublisher(myPublisher1);
        mySubscription.addPublisher(myPublisher2);


        /* 2. 创建一个 订阅者 实例 */
        MySubscriber mySubscriberA = new MySubscriber("SA");
        MySubscriber mySubscriberB = new MySubscriber("SB");
        MySubscriber mySubscriberC = new MySubscriber("SC");



        /* 3. 将 订阅者 注册到 发布者 中 */
        myPublisher1.subscribe(mySubscriberA);
        myPublisher2.subscribe(mySubscriberB);
        myPublisher2.subscribe(mySubscriberC);


        /* 4. 生产数据，并发布 */
        myPublisher1.startProduce();
        myPublisher2.startProduce();

        /* 5. 消费者,消费 */
        mySubscriberA.startConsume();
        mySubscriberB.startConsume();
        mySubscriberC.startConsume();


        // new Thread(()->{
        //     while (true) {
        //         List<Integer> data = myPublisher1.getData(4);
        //         for (Integer integer : data) {
        //             System.out.println(" 消费：" + integer);// 相当于处理逻辑
        //             try {
        //                 TimeUnit.SECONDS.sleep(2); // 两秒处理一个
        //             } catch (InterruptedException e) {
        //                 e.printStackTrace();
        //             }
        //         }
        //     }
        //
        // }).start();


        /*
         * 5. 结束后，关闭发布者
         */

    }



}
