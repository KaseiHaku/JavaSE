package kasei.javase.se.syntax.lambdaexpression;

/** 函数式接口：即一个接口中有且只能有一个抽象方法 */
@FunctionalInterface // 该注解修饰一个接口，表明当前接口是一个函数式接口
public interface LambdaExpressionInterface {
    public abstract String print(String name, Integer age);
}
