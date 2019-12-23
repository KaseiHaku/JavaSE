package kasei.demo.synchronize;


/** synchronized 关键字，类锁，修饰类方法的形式
 * 原理：
 * 1. 我们没创建一个类 Custom ，其实创建的是一个实例，而该实例的类是 java.lang.Class， Java 中 Class 类是类的类
 * 2. JVM 加载 Custom 类时会在内存中创建一个 java.lang.Class 类的实例。该实例是 Custom 类本身的运行时数据结构
 * 3. 而我们自己 new Custom() 出来的实例，是根据 Custom_Prototype 创建一个包含具体数据内容的数据结构
 * 4. 总结: java.lang.Class 的实例 kasei.Custom 的实例 new Custom()
 * */
public class Synchronized5 implements Runnable {

    static int i = 0;
    static Synchronized5 instance1 = new Synchronized5();
    static Synchronized5 instance2 = new Synchronized5();

    @Override
    public void run() {
        func();
    }

    public static synchronized void func(){
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
        Thread t2 = new Thread(instance2); // 必须采用同一个实例
        t1.start();
        t2.start();
        while(t1.isAlive() || t1.isAlive()){ // 另一种方式，让 main 线程等待 t1，t2 两个线程执行完后再往下执行

        }
        System.out.println("finished");
    }
}
