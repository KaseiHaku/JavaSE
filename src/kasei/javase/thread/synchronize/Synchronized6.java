package kasei.demo.synchronize;


/** synchronized 关键字，类锁，代码块形式
 * */
public class Synchronized6 implements Runnable {

    static int i = 0;
    static Synchronized6 instance1 = new Synchronized6();
    static Synchronized6 instance2 = new Synchronized6();

    @Override
    public void run() {
        func();
    }

    public void func(){
        synchronized (Synchronized6.class) {
            i++;
            System.out.println(Thread.currentThread().getName() + "开始执行");
            try {
                Thread.sleep(3000); // 让当前线程睡眠 3秒钟，为了让同步效果更明显
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完成");
        }

    }




    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2); // 必须采用同一个实例
        t1.start();
        t2.start();
        while(t1.isAlive() || t1.isAlive()){ // 另一种方式，让 main 线程等待 t1，t2 两个线程执行完后再往下执行

        }
        System.out.println("finished");
    }
}
