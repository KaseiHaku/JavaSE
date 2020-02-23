package kasei.javase.juc.communication;

import javax.sound.midi.Soundbank;
import java.util.concurrent.TimeUnit;

public class VolatileDemo {

    // 保证可见性
    // 不保证原子性
    // 禁止指令重排
    private volatile static int num = 0;

    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{
            while(num == 0){
                System.out.println("线程还在执行");
            }
        }).start();


        TimeUnit.SECONDS.sleep(1);
        num = 1;
        System.out.println(num);
    }
}
