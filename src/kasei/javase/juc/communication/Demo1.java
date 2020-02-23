package kasei.javase.juc.communication;

import javax.xml.crypto.Data;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** TODO 演示线程通信： synchronized 版本 */
public class Demo1 {

    static class Data {
        private int number = 0;

        public synchronized void increment() throws InterruptedException {

            if(number>0){
                this.wait(); // wait 方法，当该线程被唤醒的时候，从该行代码开始往后执行，会造成虚假唤醒，解决方案，将 if 判断改成 while
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            this.notifyAll();  // 通知其他线程，我 +1 完毕了

        }

        public synchronized void decrement() throws InterruptedException {

            if(number==0){
                this.wait(); // wait 方法，当该线程被唤醒的时候，从该行代码开始往后执行，会造成虚假唤醒，解决方案，将 if 判断改成 while
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            this.notifyAll();  // 通知其他线程，我 +1 完毕了

        }

    }

    public static void main(String[] args) {
        Data data = new Data();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();


        // TODO 以下两个线程是为了演示  虚假唤醒的问题
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();


        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }


}
