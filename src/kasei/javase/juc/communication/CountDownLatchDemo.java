package kasei.javase.juc.communication;

import java.util.concurrent.CountDownLatch;


public class CountDownLatchDemo {


    public static void main(String[] args) throws InterruptedException {
        // 并发编程中，一个线程需要等待其他线程执行完毕后，再执行，需要使用 CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(6); // 指定初始数量

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " go out");
                countDownLatch.countDown(); // 数量 -1，当数量为 0 时，将会唤醒调用当前 countDownLatch.await() 的线程
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();  // 等待 countDownLatch 归零，然后当前线程再向下走
        System.out.println("close door!");


    }


}
