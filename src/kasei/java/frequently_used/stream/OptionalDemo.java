package kasei;


import java.util.Optional;
import java.util.function.Function;

/**
 * Java 8 Optional is the way to resolve NullPointException
 * Optional 相当于一个 Container，但是跟 List 不同的是，Optional 只能容纳一个实例
 * Optional 设计用于当做方法的返回值，不能当做 Class 的 Field 和 Method 的 Param
 * */
public class OptionalDemo {




    public void optionalOperator(){
        Optional<StringBuilder> optSbNotNull = Optional.of(new StringBuilder("of 方法构建 Optional 实例，不能为 null"));
        Optional<StringBuilder> optSbNullable = Optional.ofNullable(null);
        Optional<StringBuilder> optSbNull = Optional.empty(); // 直接构建里一个容纳 null 的 Optional 实例
        


        // get value
        StringBuilder stringBuilder = optSbNotNull.get(); // 如果 optSbNotNull 会报空指针异常
        StringBuilder substitute = optSbNullable.orElse(new StringBuilder("替代品")); // 如果 optSbNullable 为 null 则返回 替代品，
        optSbNullable.orElseThrow(IllegalAccessError::new); // 如果空 抛异常
        optSbNullable.orElseGet(StringBuilder::new); // 如果 null 自定义默认值 构建方法


        // deal
        Function<StringBuilder, StringBuilder> function = x -> x.append("map 方法的返回值是 Optional 容器内部存放的 类型");
        Optional<StringBuilder> optSb2 = optSbNullable.map(function);


        Optional<StringBuilder> optSb3 = optSbNullable.flatMap(x -> Optional.of(new StringBuilder("flatMap 方法返回值是 Optional 类型，内部必须封装好")));

    }






}
