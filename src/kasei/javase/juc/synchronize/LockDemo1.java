package kasei.javase.juc.synchronize;

/** TODO 演示没有做并发控制时，会出现的错误 */
public class LockDemo1 {

    static class Ticket {
        private int number = 100;

        public void sale(){
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + " 卖出了第 " + (number--) + " 号票, 剩余："+ number);
            }
        }
    }


    public static void main(String[] args) {

        // 并发同步前提：多个线程操作同一个资源类，把资源类丢入线程
        Ticket ticket = new Ticket();


        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() ->{
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "C").start();
    }

}
