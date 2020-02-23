package kasei.javase.juc;

import java.util.concurrent.*;

public class ThreadCreate {


    /** TODO 演示通过继承 Thread 类的方式创建线程，不推荐使用 */
    public static void threadCreateWay1(){

        class DomainClassThread extends Thread {
            @Override
            public void run() {
                super.run();

                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(1);
                        System.out.println("extends Thread way = " + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        DomainClassThread t = new DomainClassThread();
        t.start();
    }


    /** TODO 演示通过实现 Runnable 接口的方式创建线程，不推荐使用 */
    public static void threadCreateWay2(){

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(1);
                        System.out.println("implements Runnable way = " + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        t.start();
    }

    public static void threadCreateWay4() throws ExecutionException, InterruptedException {
        Callable callable = () -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1);
                    if(i>50){
                        throw new Exception("i>50 ");
                    }

                    System.out.println("implements Callable way = " + i);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return "callable success";
        };

        FutureTask<String> futureTask = new FutureTask<>(callable);  // FutureTask 是 Callable Runnable 的适配器
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }




    /** TODO 演示通过实现 Callable 接口的方式创建线程，推荐使用 */
    public static void threadCreateWay3(){


        // todo 创建子线程异常处理器
        Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                e.printStackTrace();
            }
        };

        // TODO 创建线程池参数
        int corePoolSize = 2; 
        int maximumPoolSize = 4; 
        long keepAliveTime = 100; 
        TimeUnit unit = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(8);
        ThreadFactory threadFactory = (task) -> {
                    Thread t1 = new Thread(task);
                    t1.setUncaughtExceptionHandler(handler);
                    return t1;
                };
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
        
        /** TODO 线程池总结
         * 1. 如果当前线程池中的线程 < corePoolSize ，则每来一个任务，就会创建一个线程去执行这个任务，不管线程池中是否有空闲线程
         * 2. 如果当前线程池中的线程 >= corePoolSize，则每来一个任务，尝试添加该任务到缓存队列中，
         *    若成功，则等待空闲线程来将其取出并执行；若失败（队列已满），则尝试创建新的线程取执行这个任务
         * 3. 如果当前线程池中的线程数目达到 maximumPoolSize，则会采取任务拒绝策略进行处理
         * 4. 如果线程池中的线程 > corePoolSize 时，如果某线程空闲时间超过 keepAliveTime ，线程将被终止，直至线程池中线程数目不大于 corePoolSize
         *    如果允许为核心线程设置存活时间，那么核心线程空闲时间超过 keepAliveTime，核心线程也会被终止
         * 
         * 线程池拒绝策略：
         *    DiscardPolicy：直接丢弃当前任务
         *    DiscardOldestPolicy：丢弃最老的任务
         *    AbortPolicy：抛异常，会中断调用者线程
         *    CallerRunsPolicy：返回任务给调用者线程执行
         *
         * 线程池最大线程数如何设置？
         *    1. CPU 密集型：几核，就是几，可以保持 CPU 的效率最高，Runtime.getRuntime().availableProcessors()
         *    2. IO 密集型：
         *      如果程序中有 15 个大型任务，IO 十分占用资源，那么设置最大线程数为 30 个
         * */
        ExecutorService executor = new ThreadPoolExecutor(
                corePoolSize,               // 核心线程数
                maximumPoolSize,            // 最大线程数
                keepAliveTime,              // 空闲线程最大存活时间
                unit,                       // 存活时间的单位
                blockingQueue,              // 阻塞队列
                threadFactory,              // 线程工厂
                rejectedExecutionHandler    // 任务拒绝策略
        );

        // todo 实现 Callable 接口
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {


                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(1);
                        if(i>50){
                            throw new Exception("i>50 ");
                        }

                        System.out.println("implements Callable way = " + i);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                return "callable success";
            }
        };

        // 运行线程
        Future<String> submit = executor.submit(callable); // callable 接口的实现类只能使用 submit 提交才能有返回值

        try {
            String subThreadReturn = submit.get();
            System.out.println(subThreadReturn);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        // threadCreateWay1();
        // threadCreateWay2();
        // threadCreateWay3();
        threadCreateWay4();
    }

}
