package kasei.javase.se.thread.correspond.demo1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 演示共享内存的方式，实现线程间通信和同步
public class Ticket implements Runnable {

    private Integer num = 100;

    private Object lock = new Object() ;//创建锁对象

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (num > 0) {
                    try{
                        Thread.sleep(10);
                    }catch (InterruptedException e){

                    }

                    System.out.println(Thread.currentThread().getName()+".....sale...."+num--);
                }
            }
        }
    }
}
