package kasei.javase.se.objectoriented.classes;


import com.sun.org.apache.xpath.internal.SourceTree;
import kasei.javase.se.objectoriented.classes.other.InterfaceA;
import kasei.javase.se.objectoriented.classes.other.InterfaceB;

//接口的定义规则：注意接口是可以 extends 多个其他接口的，但不能是 抽象类
public interface Interface extends InterfaceA, InterfaceB {
	public static final String str = "interface";
	public abstract void print();
	public default void defaultPrint(){
        System.out.println("Default Interface Method");
    }
}
