package kasei.demo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    Lock lock = new ReentrantLock();


    public synchronized void func1(){
        System.out.println("Synchronized 形式的锁");
    }


    public void func2(){
        lock.lock(); // 获得锁，拿不到锁就一直等待，比较无赖的做法
        lock.tryLock(); // 尝试获取一把锁，立马返回，拿到返回 ture 没拿到返回 false，比较潇洒的做法
        //lock.tryLock(1000, TimeUnit.SECONDS); // 尝试获取一把锁，拿不到锁就等待一定时间，超时返回 false，比较聪明的做法
        lock.newCondition();// 生成获取锁的条件
        //lock.lockInterruptibly();
        try {
            System.out.println("Lock 形式的锁");
        }finally {
            lock.unlock(); // Lock 形式的锁，必须手动释放锁
        }
    }

    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();
        lockDemo.func1();
        lockDemo.func2();

    }
}
