package kasei.javase.juc.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** TODO 演示线程通信： Lock 版本 */
public class Demo2 {

    static class Data {
        private int number = 0;
        final Lock lock = new ReentrantLock(false);
        final Condition condition = lock.newCondition();// 生成获取锁的条件

        public void increment(){
            lock.lock();
            try {
                if(number!=0){    // Lock 版本中使用 if 同样存在 虚假唤醒 的问题
                    condition.await(); // 等价于 Object.wait() 方法
                }
                number++;
                System.out.println(Thread.currentThread().getName() + "=>" + number);
                condition.signalAll();  // 等价于 Object.notifyAll() 方法，通知其他线程，我 +1 完毕了
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void decrement(){
            lock.lock();
            try {
                if(number==0){
                    condition.await();
                }
                number--;
                System.out.println(Thread.currentThread().getName() + "=>" + number);
                condition.signalAll(); // 通知其他线程，我 -1 完毕了
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
                data.increment();
            }
        }, "A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        }, "B").start();


        // TODO 以下代码用于演示：虚假唤醒
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        }, "C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        }, "D").start();

    }


}
