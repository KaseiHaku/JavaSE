package kasei.javase.juc.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** TODO 演示线程通信： Lock 版本，演示 Condition 精准的唤醒指定的线程 */
public class Demo3 {

    static class Data {
        private int number = 1;
        final Lock lock = new ReentrantLock(false);
        final Condition condition1 = lock.newCondition();// 线程 A 的
        final Condition condition2 = lock.newCondition();// 线程 B 的
        final Condition condition3 = lock.newCondition();// 线程 C 的

        public void printA(){
            lock.lock();
            try {
                while(number!=1){    // Lock 版本中使用 if 同样存在 虚假唤醒 的问题
                    condition1.await(); // 等价于 Object.wait() 方法
                }
                number++;
                System.out.println(Thread.currentThread().getName() + "=>AAAA");
                condition2.signal();  // 精准的通知 condition2 的所有线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printB(){
            lock.lock();
            try {
                while(number!=2){
                    condition2.await();
                }
                number++;
                System.out.println(Thread.currentThread().getName() + "=>BBBB");
                condition3.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printC(){
            lock.lock();
            try {
                while(number!=3){
                    condition3.await();
                }
                number = 1;
                System.out.println(Thread.currentThread().getName() + "=>CCCC");
                condition1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }


    }

    public static void main(String[] args) {
        Data data = new Data();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        }, "A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        }, "B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        }, "C").start();

    }


}
