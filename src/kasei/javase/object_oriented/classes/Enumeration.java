
package kasei.javase.se.objectoriented.classes;

import java.util.Arrays;



/**
 * enum 关键字，是 public class Enumeration extends java.lang.Enum{} 的语法糖
 * java.lang.Enum 包含两个字段： ordinal, name
 * ordinal 默认从 0 开始，每次 +1，可以赋值，赋值后，之后的值在赋值数字的基础上 +1
 * */
public enum Enumeration implements MyInterface { // 枚举类可以实现接口

    //枚举类型是 常量 跟 'a' 一样，不能实例化的（即不能 new 和 Colors colors 定义引用）
    RED         (1, "red"),     // 调用构造函数，来构造 RED 实例，其中 (1,"red") 是构造函数的实参
    GREEN       (2,"green"){
        
        // 因为该方法在 Enumeration 类中是抽象方法，而 GREEN 是 Enumeration 的实例，
        // 因为抽象类不能创建实例，所以 GREEN 肯定需要实现抽象方法
        @Override
        public String asLowerCase() {
            return HIGH.toString().toLowerCase();
        }
    }, 
    BULE        (3,"blue"), 
    YELLOW      (4,"yellow"), 
    PURPLE      (5,"purple"), 
    PINK        (6,"pink")      //这些就是枚举类的实例，括号内的相当于构造函数的参数
    ;


    private Integer value;
    private String foo;


    private Enumeration(Integer value, String foo){
        this.value = value;
        this.foo = foo;    
    }

    /** TODO 该抽象方法必须在枚举实例中实现 */
    public abstract String asLowerCase();

    public static void main(String[] args) {

        Enumeration color = null;

        // 枚举类 类方法
        Enumeration[] ary = Enumeration.values();
        Enumeration color1 = Enumeration.valueOf(Enumeration.class, "PURPLE");
        Enumeration color2 = Enumeration.valueOf("GREEN");

        System.out.println(Arrays.toString(ary));
        System.out.println(color1);
        System.out.println(color2);

        // 枚举类 实例方法
        System.out.println(
                "Instance:\n"+
                Enumeration.RED.ordinal()+"\n"+
                Enumeration.RED.getDeclaringClass()+"\n"+
                Enumeration.RED.name()+"\n"+
                Enumeration.RED.toString()+"\n"+
                Enumeration.RED.compareTo(Enumeration.GREEN)
        );
    }
}
