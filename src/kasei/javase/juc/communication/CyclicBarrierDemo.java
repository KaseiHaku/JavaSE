package kasei.javase.juc.communication;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{  // 初始化需要等待的线程数量
            System.out.println("召唤神龙成功");
        });

        for (int i = 0; i < 7; i++) {
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "收集" + temp + "个龙珠");

                try {
                    cyclicBarrier.await();// 等待，每调用一次 await 会增加一次计数，当await 的个数等于初始设置的线程个数，就执行指定线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }


            }).start();
        }


    }

}
