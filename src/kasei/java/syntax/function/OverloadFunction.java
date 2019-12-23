package kasei.javase.se.syntax.function;


/* Java 重载(Overload)与重写(Override)
重载：两同（同一个类、同一个方法名）三不同（参数列表不同：类型、个数、顺序），返回类型不同不算，会报错
子类中存在与父类函数名相同，参数列表不同的函数，不算重载，重载只能发生在一个类内
*/
public class OverloadFunction {

    void fun(char ch)
    {
        System.out.println("Overload：重载演示比较参照方法");
    }

    void fun(int i, String str)
    {
        System.out.println("Overload：参数类型和个数不同");
    }
    void fun(String str, int i)
    {
        System.out.println("Overload：参数顺序不同！");
    }

    //int fun(){ System.out.println("仅返回类型不同"); };
    //出错  仅返回类型不同不是重载
}
