package kasei.javase.se.thread.correspond.demo3;

public class MultipleProducerConsumer {

    public static void main(String[] args) {
        KaoYa r = new KaoYa();
        ProducerMultiple pro = new ProducerMultiple(r);
        ConsumerMultiple con = new ConsumerMultiple(r);
        //生产者线程
        Thread p1 = new Thread(pro);
        Thread p2 = new Thread(pro);
        //消费者线程
        Thread c1 = new Thread(con);
        Thread c2 = new Thread(con);
        //启动线程
        p1.start();
        p2.start();
        c1.start();
        c2.start();

    }
}
