

public class SingletonPattern {

    /** todo 饿汉式 */
    public static final class HungrySingleton {
        private static final HungrySingleton instance = new HungrySingleton(); // 静态字段，类加载时就执行

        private HungrySingleton(){}
        public static HungrySingleton getInstance(){
            return instance;
        }
    }

    /** todo 懒汉式 */
    public static final class LazySingleton {
        // private static LazySingleton instance = null;
        private static volatile LazySingleton instance = null; // 添加 volatile 阻止指令重排序，解除双重锁隐患

        private LazySingleton(){}



        /** 由于 getInstance1() 不是同步方法，所以在多线程下会有同步问题，改进为 getInstance2() */
        public static LazySingleton getInstance1(){
            if (instance == null) {
                instance = new LazySingleton();
            }
            return instance;
        }

        /** 由于 getInstance2() synchronized 关键字是加在方法上的，每次获取实例都会加锁，从而导致性能问题，改进为 getInstance3() */
        public static synchronized LazySingleton getInstance2(){
            if (instance == null) {
                instance = new LazySingleton();
            }
            return instance;
        }

        /** 由于 getInstance3() 判断 instance == null 是非同步代码，所以有可能多个线程同时判定 instance 为 null，从而进入同步代码块，并创建多个实例，改进为 getInstance4() */
        public static synchronized LazySingleton getInstance3(){
            if (instance == null) {
                synchronized (LazySingleton.class){
                    instance = new LazySingleton();
                }
            }
            return instance;
        }

        /** 双检锁机制(double-check)：进入同步代码块再做一次非空校验
         * 隐患：
         *      指令重排优化：是指在不改变原语义的情况下，通过调整指令的执行顺序让程序运行的更快
         * 正常情况下的 JVM 创建新实例的过程：
         *      1. 分配内存块 -> 2. 调用构造方法初始化内存数据结构 -> 3. 将分配的内存首地址分配给 instance 变量 -> 4. 完成新实例创建
         * Java 指令重排优化后，可能导致的异常新实例创建过程：
         *      1. 分配内存块 -> 2. 将内存块设置默认值 -> 3. 将分配的内存首地址分配给 instance 变量 -> 4. 调用构造方法覆盖默认值 -> 5. 完成新实例创建
         * 原因:
         *      多线程的情况下，如果在异常实例创建过程中，另外一个线程在一号检查时刚好是 异常创建的第 4 步这个节点前，那么新实例还是默认值状态，但不 = null,
         *      就会造成该线程获取到的实例是默认值的实例，造成 双重锁检查机制失效
         * 解决方法：
         *      在 instance 字段上天加 volatile 关键字，volatile 关键字可以阻止编译器进行指令重排序优化
         *
         * */
        public static synchronized LazySingleton getInstance4(){
            if (instance == null) {                     // 1 号非空检查
                synchronized (LazySingleton.class){
                    if(instance == null){               // 2 号非空检查
                        instance = new LazySingleton();
                    }
                }
            }
            return instance;
        }
    }


    /** todo 内部类方式 */
    public static final class StaticInnerClassSingleton {
        private static class SingletonCreatorInnerClass{
            public static StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
        }

        private StaticInnerClassSingleton(){}
        public static StaticInnerClassSingleton getInstance(){
            return SingletonCreatorInnerClass.instance;
        }

    }

    /** TODO 枚举类方式：唯一一种不会被破坏的单例模式 */
    public static enum EnumSingleton {
        INSTANCE;
        private EnumSingleton(){}
        public static EnumSingleton getInstance(){
            return INSTANCE;
        }
    }




    public static void main(String[] args) {


    }
}
