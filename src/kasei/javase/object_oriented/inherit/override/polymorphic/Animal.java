package kasei.javase.se.objectoriented.inherit.override.polymorphic;

public class Animal {

    public String str ="叫";
    private int i =9;


    public Animal() {
        draw();//构造方法中调用了子类重写的方法
    }

    private void f(){
        System.out.println("多态缺陷：子类重写父类私有函数时子类重写的方法也不能访问！");
    }

    void fun(){
        System.out.println("动物性格！");
    }

    void draw(){ // 该方法被 Dog 重写
        System.out.println("多态缺陷3：当父类构造函数中调用被子类重写的方法时：A 中的 draw 方法");
    }
}
