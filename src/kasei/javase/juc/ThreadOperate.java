package kasei.javase.juc;

import java.util.concurrent.TimeUnit;

public class ThreadOperate {


    /** TODO sleep 方法
     * 企业开发中一般使用 TimeUnit.DAYS.sleep(1); 来执行该方法
     * */
    public static void sleep(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(1000);   // sleep 不释放琐资源
                        System.out.println("sleep = " + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    public static void join(){
        Thread t = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);   // sleep 不释放琐资源
                    System.out.println("sleep = " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        for(int i=0; i<10; i++){//主线程
            if(i==5) {
                try {
                    System.out.println("join()执行");
                    t.join();//把 t 线程合并到当前（main）线程 中，main线程阻塞，等待t线程执行完成
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Main: "+i);
        }
    }


    /** interruput 操作一般配合一个 stopFlag 共享变量一起使用，当 stopFlag 变更后，
     * 应立即调用 t.interrupt() 方法修改 t 线程实例的 打断标志, 如果此时 t 线程在 阻塞 或者 等待状态，就会抛出 InterruptedException 异常
     * 通过 catch 该异常，来让 t 线程自行决定执行逻辑
     * */
    public static void interrupt(){
        Thread t = new Thread(()->{
            try {
                for (int i = 0; !Thread.currentThread().isInterrupted() && i<100; i++) {
                    // 只有在线程处于阻塞状态时被 interrupt 才会抛 InterruptedException 异常，可以选择注释掉该行进行对比
                    // 更准确的说：当当前线程不占用 CPU 时间片的时候，被 interruput 就会抛出 InterruptedException 异常，
                    // 并使当前线程 退出 阻塞 或者 等待状态，重新进入 可执行状态
                    Thread.sleep(1000);   
                    System.out.println("sleep = " + i);
                }
            } catch (InterruptedException e) { // 该异常会清除 interrupt 标记
                e.printStackTrace();
            }
        });
        t.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt(); // 5 秒后打断 t 线程
    }

    /** yield:暂停自己的线程，并执行其他的线程，但是不能阻止CPU再次调用自己的线程 */
    public static void yield(){

        Thread t = new Thread(()->{
            try {
                for (int i = 0; i<30; i++) {
                    Thread.sleep(1000);   // 只有在线程处于阻塞状态时被 interrupt 才会抛 InterruptedException 异常，可以选择注释掉该行进行对比
                    System.out.println("sleep = " + i);
                }
            } catch (InterruptedException e) { // 该异常会清除 interrupt 标记
                e.printStackTrace();
            }
        });
        t.start();

        for(int i=0; i<100; i++){//主线程
            if(i%4==0) {
                System.out.println("yield()执行");
                Thread.yield();//暂停本线程，这条语句写在谁的线程体中就暂停谁
            }
            System.out.println("Main: "+i);
        }
    }

    public static void main(String[] args) {
        // sleep();
        // join();
        // interrupt();
        yield();
    }
}
