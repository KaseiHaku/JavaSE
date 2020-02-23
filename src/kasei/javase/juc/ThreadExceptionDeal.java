package kasei.javase.juc;

import java.util.concurrent.*;

public class ThreadExceptionDeal {


    public static void threadExceptionDealWay1(){
        Runnable runnable = () -> {
            try {
                throw new Exception("子线程异常处理：在线程运行代码中直接捕获所有异常");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();
    }

    public static void threadExceptionDealWay2(){
        Runnable runnable = () -> {
            throw new RuntimeException("子线程异常处理：设置线程默认异常处理器");
        };
        Thread t = new Thread(runnable);
        t.setUncaughtExceptionHandler((Thread t1, Throwable e) -> {
            e.printStackTrace();
        });
        t.start();
    }

    public static void threadPoolExceptionDealWay1(){
        ThreadFactory threadFactory = r -> {
            Thread t1 = new Thread(r);
            t1.setUncaughtExceptionHandler((t, e) -> {
                e.printStackTrace();
            });
            return t1;
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                4,
                100,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(8),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy()
        );

        threadPoolExecutor.execute(()->{
            throw new RuntimeException("线程池异常处理：覆写 ThreadFactory ，并设置线程默认异常处理器；仅对 execute() 提交的任务有效");
        });
    }
    public static void threadPoolExceptionDealWay2(){
        class CustomThreadPoolExecutor extends ThreadPoolExecutor {
            public CustomThreadPoolExecutor(
                    int corePoolSize,
                    int maximumPoolSize,
                    long keepAliveTime,
                    TimeUnit unit,
                    BlockingQueue<Runnable> workQueue,
                    ThreadFactory threadFactory,
                    RejectedExecutionHandler handler) {
                super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                if (t != null) {
                    t.printStackTrace();
                }
            }
        }

        CustomThreadPoolExecutor customThreadPoolExecutor = new CustomThreadPoolExecutor(
                2,
                4,
                100,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(8),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        customThreadPoolExecutor.execute(()->{
            throw new RuntimeException("线程池异常处理：继承 ThreadPoolExecutor 覆写 afterExecute()；仅对 execute() 提交的任务有效");
        });
    }
    public static void threadPoolExceptionDealWay3(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                4,
                100,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(8),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                String str = "1234";
                if(str.length()<=4){
                    throw new Exception("线程池异常处理：通过返回 Future 实例捕获异常");
                }
                return "no ";
            }
        };
        Future<String> submit = threadPoolExecutor.submit(callable);

        try {
            String s = submit.get();
            System.out.println(s);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }





    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程全局异常处理器：" + e.getMessage());
            }
        });

        threadExceptionDealWay1();
        threadExceptionDealWay2();
        threadPoolExceptionDealWay1();
        threadPoolExceptionDealWay2();
        threadPoolExceptionDealWay3();
    }


}
