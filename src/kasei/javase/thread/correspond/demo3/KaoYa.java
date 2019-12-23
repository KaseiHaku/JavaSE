package kasei.javase.se.thread.correspond.demo3;

public class KaoYa {

    private String name;
    private Integer count = 1;  // 烤鸭初始数量
    private Boolean flag = false; // 判断是否有需要线程等待的标志

    public synchronized void produce(String name){
        while (flag) {  // 如果不使用 while 线程被唤醒的时候是从 wait() 之后直接往下执行的，不会重新判断等待标志，所以要循环判断，不成立则往下执行
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.name = name + count;
        count ++;
        System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);
        flag=true;//有烤鸭后改变标志
        notifyAll();//通知消费线程可以消费了
    }

    public synchronized  void consume(){
        while(!flag){//如果没有烤鸭就等待
            try{
                this.wait();
            }catch(InterruptedException e){

            }
        }

        System.out.println(Thread.currentThread().getName()+"...消费者........"+this.name);//消费烤鸭1
        flag = false;
        notifyAll();//通知生产者生产烤鸭
    }
}
