package kasei.javase.juc.synchronize;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** TODO 演示使用 Lock 接口做并发控制 */
public class LockDemo2 {

    static class Ticket {
        private int number = 100;
        Lock lock = new ReentrantLock();

        public void sale(){

            lock.lock(); // 获得锁，拿不到锁就一直等待，比较脑残的做法
            // lock.tryLock(); // 尝试获取一把锁，立马返回，拿到返回 ture 没拿到返回 false，比较潇洒的做法
            //lock.tryLock(1000, TimeUnit.SECONDS); // 尝试获取一把锁，拿不到锁就等待一定时间，超时返回 false，比较聪明的做法


            //lock.lockInterruptibly();
            try{
                if (number > 0) {
                    System.out.println(Thread.currentThread().getName() + " 卖出了第 " + (number--) + " 号票, 剩余："+ number);
                }
            } finally {
                lock.unlock(); // Lock 形式的锁，必须手动释放锁
            }


        }
    }


    public static void main(String[] args) {

        // 并发同步前提：多个线程操作同一个资源类，把资源类丢入线程
        Ticket ticket = new Ticket();

        new Thread(() -> {for (int i = 0; i < 60; i++) ticket.sale();}, "A").start();
        new Thread(() -> {for (int i = 0; i < 60; i++) ticket.sale();}, "B").start();
        new Thread(() -> {for (int i = 0; i < 60; i++) ticket.sale();}, "C").start();
    }

}
