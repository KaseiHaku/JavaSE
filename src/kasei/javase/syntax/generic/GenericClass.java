package kasei.springcloud.basic.common.exception;

import java.util.Arrays;
import java.util.List;

/**
 * 泛型基础知识：
 *  1. 泛型实参: ?, Integer, Number, String; 所以泛型实参是一个具体的泛型类型，其中 ? 相当于 Object
 *  2. 泛型形参: T, E, K, V
 *  3. 泛型通配符: <? extends Number>, <? super Shape>, <?>;  泛型通配符只能用于声明(Java 中声明和定义都是同时进行的)，实际使用时不能使用
 *
 * 什么是声明 or 定义？
 *  1. 变量声明：List<> list;   
 *  2. 方法声明：public void func(List<?> list){};  
 *  3. 类的声明：public class Aa<?>{}
 *
 *
 *
 * 什么是实际使用?
 *  1. 变量的使用：List<?> list = new ArrayList<String>();   // 其中 等号后面的是定义
 *  2. 方法的使用：func(listVar);  // 方法调用
 *  3. 类的使用：new Aa(); // 使用类创建对象
 */
public class GenericClass<T, U>{
    
}


class GenericMethod {

    /** TODO 只有方法前面有 <T super String> 标识的方法，才是泛型方法 */
    public static <T extends Object, E> T method(T param){
        return null;
    }
    public static <T> T method2(String str){
        Object obj = null;
        return (T)obj
    }

    /** TODO 泛型方法的调用 */
    public void genericMethodInvoke(){
        GenericMethod.<String, Integer>method("");  // 静态泛型方法调用时，可以在 className.<> 后加入泛型
        this.<String, Integer>method("");  // 类内部调用泛型方法时，可以在 this.<> 后加入反省

        GenericMethod instance = new GenericMethod();
        instance.<String, Integer>method(""); // 通过实例调用泛型方法， instance.<>
        
        Map map = instance.method2(""); // 调用返回值是 泛型 的方法，强制转换在 方法内部已经做了，在调用时不用写
        
    }


    /** TODO 虽然 Integer 是 Number 的子类，但是 List<Integer> 不是 List<Number> 的子类，两者是完全不同的类 */
    public void method1(List<Number> list) { }
    public void method1Test(){
        List<Integer> list = Arrays.asList(1, 2, 3);
        method1(list);  // 这里报错, 因为 List<Integer> 不是 List<Number> 的子类
    }

    public void method2(List<?> list) { }
    public void method3(List<? extends Object> list) { }
    public void method2Test(){
        List<Integer> list = Arrays.asList(1, 2, 3);
        method2(list);  // 这里不报错, 因为 List<Integer> 是 List<?> 的子类
        method3(list);  // 这里不报错, 因为 List<Integer> 是 List<? extends Object> 的子类
    }


}

