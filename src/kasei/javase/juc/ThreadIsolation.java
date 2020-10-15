package kasei.javase.juc;



/** todo 用于演示 ThreadLocal 类型，用于线程隔离 
 * ThreadLocal Memory Leak Origin
 *  [threadLocalVarAddr:threadLocalInsAddr]  // ThreadLocal 变量，threadLocalVarAddr 表示该变量的内存地址， threadLocalInsAddr 表示 ThreadLocal 实例的内存地址
 *  [threadLocalInsAddr:threadLocalInsContent]  // threadLocalInsContent 表示 ThreadLocal 实例的内容
 *  当 set(v) 时，会将 tlIns 的 弱引用 作为 ThreadLocal.ThreadLocalMap.Entry 的 key 进行保存
 *  [entryKeyVarAddr:weak(threadLocalInsAddr)] // entryKeyVarAddr 表示 ThreadLocal.ThreadLocalMap.Entry.key 的内存地址
 *  如果将 [threadLocalVarAddr:threadLocalInsAddr] 置为 [threadLocalVarAddr:null]，
 *  那么将没有 强引用 指向 [threadLocalInsAddr:threadLocalInsContent] 
 *  当发生 GC 时，会将 [threadLocalInsAddr:threadLocalInsContent] 回收掉，
 *  回收掉之后 [entryKeyVarAddr:weak(threadLocalInsAddr)] 将变为 [entryKeyVarAddr:weak(null)] 
 *  由于 currentThread 没有被回收(线程池)，那么 entry value 存在一条 强引用链   currentThread -> threadLocalMap -> entry -> value ,
 *  这将导致，只要 currentThread 没有停止，那么 value 就不会被回收，导致内存泄漏
 *
 *
 */
public class ThreadIsolation extends Thread {

    private static ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
    private static Integer chaos = 0;


    @Override
    public void run() {
        stringThreadLocal.set(Thread.currentThread().getName());
        for (int i = 0; i < 10; i++, chaos++) {

            System.out.println(stringThreadLocal.get()+"===="+i+"====="+chaos);
        }

    }



    public static void main(String[] args) {

        // 如果没有使用 ThreadLocal 那么打印出来的每个线程的顺序是乱的,比如上面规定 chaos 变量
        Thread t1 = new ThreadIsolation();
        Thread t2 = new ThreadIsolation();
        Thread t3 = new ThreadIsolation();

        t1.start();
        t2.start();
        t3.start();
    }
}
