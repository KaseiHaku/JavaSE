package kasei.javase.se.thread.isolation;


/** todo 用于演示 ThreadLocal 类型，用于线程隔离 */
public class IsolationThreadVarDemo extends Thread {

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
        Thread t1 = new IsolationThreadVarDemo();
        Thread t2 = new IsolationThreadVarDemo();
        Thread t3 = new IsolationThreadVarDemo();

        t1.start();
        t2.start();
        t3.start();
    }
}
