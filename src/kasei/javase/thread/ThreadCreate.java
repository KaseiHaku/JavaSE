package kasei.javase.se.thread;

public class ThreadCreate {


    
    public static void threadCreateWay1(){

        class DomainClassThread extends Thread {
            @Override
            public void run() {
                super.run();

                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(1);
                        System.out.println("extends Thread way = " + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        DomainClassThread t = new DomainClassThread();
        t.start();
    }

    public static void threadCreateWay2(){

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(1);
                        System.out.println("implements Runnable way = " + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        t.start();
    }

    public static void threadCreateWay3(){


        // todo 创建子线程异常处理器
        Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                e.printStackTrace();
            }
        };
        // todo 创建线程池
        ExecutorService executor = new ThreadPoolExecutor(
                2,
                4,
                100,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(8),
                (task) -> {
                    Thread t1 = new Thread(task);
                    t1.setUncaughtExceptionHandler(handler);
                    return t1;
                },
                new ThreadPoolExecutor.AbortPolicy()
        );

        // todo 实现 Callable 接口
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {


                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(1);
                        if(i>50){
                            throw new Exception("i>50 ");
                        }

                        System.out.println("implements Callable way = " + i);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                return "callable success";
            }
        };

        // 运行线程
        Future<String> submit = executor.submit(callable); // callable 接口的实现类只能使用 submit 提交才能有返回值

        try {
            String subThreadReturn = submit.get();
            System.out.println(subThreadReturn);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        threadCreateWay1();
        threadCreateWay2();
        threadCreateWay3();
    }

}
