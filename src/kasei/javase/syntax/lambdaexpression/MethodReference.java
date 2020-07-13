/** todo 档 Lambda Expression 格式形如 (x) -> System.out.println(x) 时，
 * 即 -> 左边的输入参数 和 右边调用单个方法的输入参数一样时，就可以写成 方法引用的格式
 * 
 * */
public class MethodReference{

    public void demo(){
        // lambda expression format
        Comsumer<String> comsumerLambda = (item) -> System.out.println(item);
        comsumerLambda.accept("data");
        
        // method reference format; static method
        // 没有 this 隐藏参数
        Comsumer<String> comsumerMethodReference = System.out::println;
        comsumerMethodReference.accept("data");
        
        
        // method reference format; instance method use instance
        // 有 this 隐藏参数，但是 this 隐藏参数在 实例方法引用时，已经分配给 实例了，所以最后只剩一个参数
        String str = new String("1234");
        Predicate predicate = str::equals; // 相当于 x -> str.equals(x);
        boolean b = Stream.of("a", "b", "c").anyMatch(predicate);
        System.out.println(b);
        
        // method reference format; instance method use Class
        // 坑： java 每个方法中，都隐含一个 this 参数，指向当前对象，当使用 Class::instanceMethod 模式引用时，隐藏参数 this 会被替换成第一个参数
        // 类实例方法 引用时，引用的方法其实有两个参数 (this, param1)
        BiFunction<String, String, Boolean> biFunction = String::equals;
        Boolean apply = biFunction.apply("sa", "sa");
        System.out.println(apply);
        
        // method reference format; constructor method
        Supplier<MethodReference> supplier = MethodReference::new;
        System.out.println(supplier.get());
    }
}
