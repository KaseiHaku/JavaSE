package kasei.demo.synchronize;


/** synchronized 关键字，对象锁，修饰实例方法的形式
 * */
public class Synchronized4 implements Runnable {

    static int i = 0;
    static Synchronized4 instance1 = new Synchronized4();

    @Override
    public void run() {
        func();
    }

    public synchronized void func(){
        i++;
        System.out.println(Thread.currentThread().getName() + "开始执行");
        try {
            Thread.sleep(3000); // 让当前线程睡眠 3秒钟，为了让同步效果更明显
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行完成");
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
