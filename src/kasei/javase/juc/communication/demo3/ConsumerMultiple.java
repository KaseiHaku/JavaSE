package kasei.javase.se.thread.correspond.demo3;

public class ConsumerMultiple implements Runnable {

    private kasei.javase.se.thread.correspond.demo3.KaoYa r;

    ConsumerMultiple(kasei.javase.se.thread.correspond.demo3.KaoYa r){
        this.r = r;
    }

    public void run(){
        while(true){
            r.consume();
        }
    }
}
