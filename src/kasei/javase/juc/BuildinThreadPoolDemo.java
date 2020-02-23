package kasei.javase.juc;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BuildinThreadPoolDemo {

    public static void main(String[] args) {
        // ExecutorService executorService = Executors.newSingleThreadExecutor();// 单个线程
        // ExecutorService executorService = Executors.newFixedThreadPool(5); // 创建固定线程池的大小
        // ExecutorService executorService = Executors.newCachedThreadPool(); // 可以随机扩展，新建线程
        ExecutorService executorService = Executors.newScheduledThreadPool(3);

        try{
            for (int i = 0; i < 10; i++) {
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " OK ");
                });
            }
        } finally {
            executorService.shutdown();
        }


        // 线程池用完，程序结束后需要关闭
    }
}
