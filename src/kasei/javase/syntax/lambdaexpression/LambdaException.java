package kasei;


import com.sun.xml.internal.bind.v2.model.runtime.RuntimeTypeInfo;

import java.util.Optional;
import java.util.function.Function;

/**
 * Java 8 Optional is the way to resolve NullPointException
 * */
public class LambdaException {


    /**
     * 由于 jdk 原生的 Function 接口的 apply 方法不能抛出异常，所以无法处理抛出 checked 异常的方法，
     * 所以需要自定义一个可以抛出异常的 函数式接口
     * */
    @FunctionalInterface
    public interface ThrowsFunction<T, R, E extends Throwable> {
        R throwsApply(T t) throws E;
    }


    /**
     * TODO 将 lambda 表达式中抛出的 checked 异常，转换成 unchecked 异常抛出
     *
     * @param throwsFunction 实现 ThrowsFunction 函数式接口的 匿名内部类 或者 Lambda 表达式
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> Function<T, R> reThrowApply(ThrowsFunction<T, R, Exception> throwsFunction){
        return t -> {
            try{
                return throwsFunction.throwsApply(t);
            } catch (Throwable e){
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * TODO 自定义方法 处理 lambda 表达式中抛出的异常
     *
     * @param throwsFunction
     * @param handler 实现 JDK 原生 Function 函数式接口的 匿名内部类 或者 Lambda 表达式，用于自定义处理 Lambda 表达式 抛出的异常
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> Function<T, R> handlerApply(Rule4.ThrowsFunction<T, R, Exception> throwsFunction, Function<Throwable, R> handler){
        return t -> {
            try{
                return throwsFunction.throwsApply(t);
            } catch (Throwable e){
                return handler.apply(e);
            }
        };
    }



    public void demo(){

        Optional<String> optStr = Optional.empty();
        Optional<String> s = optStr.map(handlerApply(x -> x.toLowerCase(), e -> {
            // 在此处处理 toLowerCase 方法抛出的 空指针异常
            e.printStackTrace();
            return null;
        }));


    }







}
