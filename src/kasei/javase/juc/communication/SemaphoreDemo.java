package kasei.javase.juc.communication;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {



    public static void main(String[] args) {

        // 信号量：相当于通行证、令牌，拿到通行证的线程才可以执行，
        // 作用：用于多个共享资源互斥的使用；并发限流，控制最大线程数量
        Semaphore semaphore = new Semaphore(3);  // 初始化令牌的数量


        for (int i = 0; i < 8; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "拿到令牌，可以停车");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "释放令牌");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); // 释放令牌
                }

            }, String.valueOf(i)).start();
        }






    }



}
