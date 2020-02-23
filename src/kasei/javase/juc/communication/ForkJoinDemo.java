package kasei.javase.juc.communication;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ForkJoinDemo {

    /** 低级的码农的写法 */
    public static void low(Long start, Long end){
        long testBegin = System.currentTimeMillis();
        Long sum = 0L;
        for (; start < end; start++) {
            sum += start;
        }

        long testEnd = System.currentTimeMillis();
        System.out.println("耗时：" + (testEnd - testBegin));
        System.out.println(sum);
    }



    static class MyForkJoinTask extends RecursiveTask<Long> {

        private Long start;
        private Long end;
        private Long temp = 10_000L; // 临界值

        public MyForkJoinTask(Long start, Long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Long compute() {
            if((end-start)<temp){
                Long sum = 0L;
                for (Long start = 0L; start < end; start++) {
                    sum += start;
                }
                return sum;
            } else {
                // 使用分支合并计算，即 ForkJoin
                Long intermediate = (start+end)/2;
                MyForkJoinTask task1 = new MyForkJoinTask(start, intermediate);
                task1.fork(); // 拆分任务，把任务压入队列
                MyForkJoinTask task2 = new MyForkJoinTask(intermediate+1, intermediate);
                task2.fork();
                return task1.join() + task2.join();
            }
        }


    }

    /** 中级码农的写法
     * 如何使用 ForkJoin？
     *  1. ForkJoinPool
     *  2. ForkJoinTask
     *  3.
     * */
    public static void middle() throws ExecutionException, InterruptedException {
        long testBegin = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyForkJoinTask myForkJoinTask = new MyForkJoinTask(0L, 10_000_000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(myForkJoinTask); // 提交任务
        submit.get();
        long testEnd = System.currentTimeMillis();
        System.out.println("耗时：" + (testEnd - testBegin));
        System.out.println(submit);
    }

    /** 高级码农的写法 */
    public static void high(){
        long testBegin = System.currentTimeMillis();
        long reduce = LongStream.range(0L, 10_000_000L).parallel().reduce(0L, Long::sum);
        long testEnd = System.currentTimeMillis();
        System.out.println("耗时：" + (testEnd - testBegin));
        System.out.println(reduce);
    }

    // 执行一个大数据求和运算
    public static void main(String[] args) throws Exception {
        // low(0L, 10_000_000L); // 191
        // middle(); // 286
        high(); // 39
    }


}
