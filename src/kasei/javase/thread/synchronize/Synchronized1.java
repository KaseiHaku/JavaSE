package kasei.demo.synchronize;


public class Synchronized1 implements Runnable {

    static int i = 0;
    static Synchronized1 instance1 = new Synchronized1();

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            i++;
            //System.out.println(Thread.currentThread().getName() + " i = " + i); // 不能在这里输出，这里输出将浪费大量的时间，足够 i++ 完成 读取 +1 写回 等三个操作，造成多线程不作并发控制时，数据差错不明显
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance1); // 必须采用同一个实例
        t1.start();
        t2.start();
        t1.join(); // join 让当前 t1 线程执行完再往下执行当前 main 线程的代码
        t2.join(); // join 让当前 t2 线程执行完再往下执行当前 main 线程的代码

        System.out.println(i);
    }
}
