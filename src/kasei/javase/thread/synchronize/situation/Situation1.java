package kasei.demo.synchronize.situation;

/**
 * 演示连个线程，一个访问静态同步方法，一个访问非静态同步方法的场景
 */
public class Situation1 implements Runnable {

    static Situation1 instance1 = new Situation1();


    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            func1();
        } else {
            func2();
        }
    }


    public static synchronized  void func1(){
        System.out.println(Thread.currentThread().getName() + " 静态同步方法开始执行");
        try {
            Thread.sleep(3000); // 让当前线程睡眠 3秒钟，为了让同步效果更明显
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 静态同步方法执行完成");
    }

    public synchronized void func2(){
        System.out.println(Thread.currentThread().getName() + " 非静态同步方法开始执行");
        try {
            Thread.sleep(3000); // 让当前线程睡眠 3秒钟，为了让同步效果更明显
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 非静态同步方法执行完成");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("finished");
    }

}
