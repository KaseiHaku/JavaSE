package kasei.javase.se.objectoriented.inherit.override.polymorphic;

public class Cat extends Animal {

    public String str ="喵";

    public void f(){
        System.out.println("多态缺陷：子类重写父类私有函数时子类重写的方法也不能访问！");
    }

    void fun(){
        System.out.println("猫：皮！");
    }
}
