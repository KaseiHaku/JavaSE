package kasei.javase.se.thread.correspond.demo3;

public class ProducerMultiple implements Runnable{

    private KaoYa r;

    ProducerMultiple(KaoYa r){
        this.r = r;
    }

    public void run(){
        while(true){
            r.produce("北京烤鸭");
        }
    }

}
