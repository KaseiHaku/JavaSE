package kasei.demo.synchronize.situation;

/**
 * 演示同步方法中调用非同步方法的场景
 */
public class Situation2 implements Runnable {

    static Situation2 instance1 = new Situation2();
    static int i = 0;

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            func1();
        } else {
            func2();
        }
    }


    public synchronized  void func1(){
        for (int j = 0; j < 100000; j++) {
            func3();
        }
    }

    public void func2(){
        for (int j = 0; j < 100000; j++) {
            func3();
        }
    }


    public void func3(){
        i++;
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

}
