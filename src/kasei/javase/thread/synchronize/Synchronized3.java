package kasei.demo.synchronize;


/** synchronized 关键字，对象锁，代码块形式的使用
 * */
public class Synchronized3 implements Runnable {

    static int i = 0;
    static Synchronized3 instance1 = new Synchronized3();
    Object lock1 = new Object();
    Object lock2 = new Object();

    @Override
    public void run() {

        synchronized (lock1) {
            i++;
            System.out.println(Thread.currentThread().getName() + " lock1 开始执行");
            try {
                Thread.sleep(3000); // 让当前线程睡眠 3秒钟，为了让同步效果更明显
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " lock1 执行完成");
        }

        synchronized (lock2) {
            i++;
            System.out.println(Thread.currentThread().getName() + " lock2 开始执行");
            try {
                Thread.sleep(3000); // 让当前线程睡眠 3秒钟，为了让同步效果更明显
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " lock2 执行完成");
        }



    }



    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance1); // 必须采用同一个实例
        t1.start();
        t2.start();
        while(t1.isAlive() || t1.isAlive()){ // 另一种方式，让 main 线程等待 t1，t2 两个线程执行完后再往下执行

        }
        System.out.println("finished");
    }
}
