package kasei.javase.se.dynamicproxy.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicProxyDemo {

    /** todo 委托类接口：JDK 动态代理要求委托类必须实现一个接口 */
    private static interface DelegatorInterface {
        public void method();
    }


    /** todo 委托类 */
    private static class Delegator implements DelegatorInterface {
        @Override
        public void method() {
            System.out.println("委托人发放内容");
        }
    }

    
    /** todo 委托类调用处理器:
     * 原理：
     *      由两组静态代理构成，
     *      1. 委托类 和 InvocationHandler 实现类组成一组静态代理，其中 委托类 就是 委托类；代理类 是 InvocationHandler 的实现类
     *      2. InvocationHandler 实现类 和 动态生成的 Proxy 类组成一组静态代理，其中 InvocationHandler 的实现类 是委托类，动态生成的 Proxy 类 是代理类
     * */
    private static class DelegatorInterfaceInvocationHandler implements InvocationHandler {

        private DelegatorInterface delegator ;

        public DelegatorInterfaceInvocationHandler(DelegatorInterface delegator){
            this.delegator = delegator;
        }

        
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            System.out.println("dynamic agent before");
            Object result = method.invoke(delegator, args);
            System.out.println("dynamic agent after");
            return result;
        }
    }


    public static void main(String[] args) {

        // 加上这句将会产生一个$Proxy0.class文件，这个文件即为动态生成的代理类文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true"); 

        
        /* 生成动态代理的代理类实例
         * Proxy.newProxyInstance(); 完全不需要 delegator 的存在，直接根据接口类 I，自动生成一个实现类 A，并创建一个 A 类的实例 a 返回 */
        DelegatorInterface delegatorInterface = (DelegatorInterface) Proxy.newProxyInstance(
                DelegatorInterface.class.getClassLoader(),
                new Class[]{DelegatorInterface.class},
                new DelegatorInterfaceInvocationHandler(new Delegator())
        );

        delegatorInterface.method();

    }

}
