package kasei.javase.se.syntax.field;

public class Field {

    private Integer var1; // 只有本类内部可以访问
    Integer var3; // 包内任何类都可访问，包外任何类不能访问（包括子类）
    protected Integer var2; // 包内任何类 以及 包外子类 能访问
    public Integer var4; // 包内及包外的任何类（包括子类和普通类）都可以访问



    transient Integer var5;// 表示序列化的时候该字段不作序列化
    volatile Integer var6; // 表示该变量容易变化，每次都从内存取值，不从缓存中取值


    //1//类内部成员方法和成员变量可以直接访问，不受修饰符影响
    //2//静态的只能访问静态的
    //3//内部类（类内部的类）：能访问类的所有成员，不能定义static成员
    //4//静态内部类（嵌套类）：只能访问外部类的静态成员
    //5//局部内部类（方法内部的类）：能访问方法内的final变量和外部类所有成员
}
