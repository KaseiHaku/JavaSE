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
        Comsumer<String> comsumerMethodReference = System.out::println;
        comsumerMethodReference.accept("data");
        
        
        // method reference format; instance method use instance
        String str = new String("1234");
        Predicate predicate = str::equals;
        boolean b = Stream.of("a", "b", "c").anyMatch(predicate);
        System.out.println(b);
        
        // method reference format; instance method use Class
        BiFunction<String, String, Boolean> biFunction = String::equals;
        Boolean apply = biFunction.apply("sa", "sa");
        System.out.println(apply);
        
        // method reference format; constructor method
        Supplier<MethodReference> supplier = MethodReference::new;
        System.out.println(supplier.get());
    }
}
