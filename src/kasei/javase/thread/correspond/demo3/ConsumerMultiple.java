package kasei.javase.se.thread.correspond.demo3;

public class ConsumerMultiple implements Runnable {

    private KaoYa r;

    ConsumerMultiple(KaoYa r){
        this.r = r;
    }

    public void run(){
        while(true){
            r.consume();
        }
    }
}
