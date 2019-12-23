package kasei.javase.se.objectoriented.classinit;

public class ClassInit2 extends ClassInit {

    public static String str1 = "子类：1、静态变量加载执行...";

    public static void print21(){
        System.out.println(str1);
        System.out.println("子类：2、静态函数加执行...");
    }

    static{
        print21();
        System.out.println("子类：3、静态语句块加载执行...");
    }

    public String str2 = "子类：4、动态变量加载执行...";

    {
        System.out.println(str2);
        System.out.println("子类：5、动态语句块加载执行...");
    }

    public void print22(){
        System.out.println("子类：6、动态函数加载执行...");
    }

    public ClassInit2(){
        print22();
        System.out.println("子类：7、构造函数加载执行...");
    }
}
