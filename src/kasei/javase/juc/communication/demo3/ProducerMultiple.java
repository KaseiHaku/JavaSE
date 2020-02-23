package kasei.javase.se.thread.correspond.demo3;

public class ProducerMultiple implements Runnable{

    private kasei.javase.se.thread.correspond.demo3.KaoYa r;

    ProducerMultiple(kasei.javase.se.thread.correspond.demo3.KaoYa r){
        this.r = r;
    }

    public void run(){
        while(true){
            r.produce("北京烤鸭");
        }
    }

}
