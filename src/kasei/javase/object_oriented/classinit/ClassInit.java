package kasei.javase.se.objectoriented.classinit;

public class ClassInit {

    public static String str1 = "父类：1、静态变量加载执行...";

    public static void print11(){
        System.out.println(str1);
        System.out.println("父类：2、静态函数加执行...");
    }

    static{
        print11();
        System.out.println("父类：3、静态语句块加载执行...");
    }

    public String str2 = "父类：4、动态变量加载执行...";

    {
        System.out.println(str2);
        System.out.println("父类：5、动态语句块加载执行...");
    }

    public void print12(){
        System.out.println("父类：6、动态函数加载执行...");
    }

    public ClassInit(){
        print12();
        System.out.println("父类：7、构造函数加载执行...");
    }
}
