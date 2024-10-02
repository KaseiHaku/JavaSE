package kasei.springcloud.basic.common.exception;

import java.util.Arrays;
import java.util.List;

/**
 * @doc {官方文档} https://docs.oracle.com/javase/tutorial/java/generics/index.html
 * 
 * 泛型基础知识：
 *  1. 泛型实参: Object, Integer, Number, String; 所以泛型实参是一个具体的泛型类型，
 *  2. 泛型形参: T, E, K, V
 *  3. 泛型通配符: <? extends clsOrIntf & intf1 & intf2>, <? extends Number>, <? super Shape>, <?>;  
 *               泛型通配符只能用于声明，不能用于定义(实际使用)
 *               当存在 多个限定(bound) 时，最多只能有一个类，且该类必须是限定列表的第一个，其他必须是接口
 *
 * 什么是声明？
 *  1. 变量声明：List<?> list;   
 *  2. 方法声明：
 *      public void func(List<?> list){};   // 方法参数类型声明
 *      public List<?> func(Integer i){};   // 方法返回值类型声明
 *  3. 类的声明：public class Aa<?>{}
 *
 *
 *
 * 什么是定义 or 实际使用? 以下为实际使用
 *  1. 变量的创建：new ArrayList<String>();       // 其中 等号后面的是定义
 *  2. 方法的调用：obj.<String>func(listVar);     // 方法调用
 *  3. 类的创建：new Aa<String>();               // 使用类创建对象
 *
 *
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


    /** 
     * @trap Java 泛型不支持 协变性
     *       例如: 虽然 Integer 是 Number 的子类，但是 List<Integer> 不是 List<Number> 的子类，两者是完全不同的类，没有任何关系
     *       因为如果支持 协变性，那么可能出现如下代码:
     *           List<Map<String, Object>> mapList = new ArrayList<>();
     *           List<Object> objList = mapList;               // 如果支持 协变性，那么这里 编译器 就不会报错 
     *           objList.add(new ArrayList<>());               // 添加一个不是 Map 的对象
     *           Map<String, Object> map = mapList.get(0);     // 这里会出现类型不匹配的问题，如果 泛型支持协变，那么就和 引入泛型的初衷(强类型) 冲突，所以 泛型不可能支持 协变性
     *
     *       如果你需要将 List<Map> 赋值给 List<Object>，可以考虑使用通配符 ?，即 List<?>
     *           List<Map<String, Object>> mapList = new ArrayList<>();
     *           List<?> objList = mapList;        // 这样赋值 编译器 才不报错
     * */
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



    /** 
     * 泛型通配符 存取解释 及 指导方针
     *    PECS（Producer Extends, Consumer Super）原则：   
     *        method body 对 通配符入参 只读不写，用 <? extends Xxx>
     *        method body 对 通配符入参 需要写入，用 <? super Xxx>
     *    方法参数类型：
     *        in  类型的方法参数: 向方法体中的代码提供数据的 入参
     *        out 类型的方法参数：保存方法体中代码的处理结果，并需要在其他地方使用的入参
     *    泛型通配符使用原则：
     *        在函数参数中，in 类型的参数，应该使用 extends 来表示
     *        在函数参数中，out 类型的参数，应该使用 super 来表示
     *        在 in 类型的参数中，只能访问 Object 类的方法时，应该使用 无界通配符(即: ?)
     *        在参数同时作为 in 和 out 时，则不应该使用 泛型通配符
     *        以上原则不适用于 方法返回值，方法返回值应该避免使用 泛型通配符，因为这会强制 程序员 使用代码来处理 通配符
     * */
    public void superWildcard(List<? super Number> data){
        Object object = data.get(1); // 只能用 Object 接住变量，因为不知道是 List<Number> 还是 List<Object>，所以统一向上转型为 Object
        data.add(new Integer(2));    // 可以添加 Number 的子类，因为不管 data 是 List<Number> 还是 List<Object>，add 一个 Integer 都没问题
    }
    public void extendsWildcard(List<? extends Number> data){
        Number object = data.get(1); // 只能用 Number 接住变量，因为不知道你传进去的到底是 List<Number> 还是 List<Integer>，所以统一向上转型为 Number
        data.add(new Integer(2)); // 错误，无法通过编译，因为不知道 data 到底是 List<Integer> 还是 List<Double>，如果是 List<Double>, 那么 add 就会出错，所以不能 add 元素
    }

    /** 泛型嵌套问题 */
    public Mono<RespDto<?>> nestedWildcard1(){
        Mono<RespDto<String>> obj = null;
        return obj; // 报错，无法通过编译，因为泛型不支持嵌套; 所以这里只会判断 RespDto<?> 和 RespDto<String> 是否相同，结果是不同，所以就报错了
    }
    public Mono<? extends RespDto<?>> nestedWildcard1(){
        Mono<RespDto<String>> obj = null;
        return obj; // 这里不报错，因为 <RespDto<String>> 是 <? extends RespDto<?>> 的子类
    }

}

