package kasei.javase.se.objectoriented.inherit.override.polymorphic;

public class Dog extends Animal {

    void fun(){
        System.out.println("狗：暖！");
    }

    void draw(){
        System.out.println("多态缺陷3：当父类构造函数中调用被子类重写的方法时：B 重写的 draw 方法");
    }
}
