
package kasei.javase.se.objectoriented.classes;

/*
JDK 注解：
    元注解：注解的注解，用于形容注解的一些特性
        @Retention          表示该元注解注解的注解会被保留到哪一个阶段，见下述“注解分类”
        @Target             表示该元注解注解的注解可以配置在哪些位置，例如 包，类，字段，方法，方法参数等
        @Repeatable         表示该元注解注解的注解是否可以重复标注，例如一个类上存在多个 @CustomAnnotation
        @Documented         表示该元注解注解的注解是否可以要在 javadoc 中显示
        @Inherited          表示                              
                                1. @A 注解了一个 class Parent
                                2. class Child extends Parent ，但是 class Child 没有被 @A 注解，
                                3. 如果 @A 注解被 @Inherited 元注解注解了，
                                    那么 class Child 即使没有显式的被 @A 注解，它也是隐式的被 @A 注解了
                                4. 如果 @A 注解没被 @Inherited 元注解注解，
                                    那么 class Child 没有显式的被 @A 注解，它就是没被 @A 注解
    @Native   
    @Override  //告知编译器其标记的方法是由父类定义的——即方法重写
    @Deprecated  //表示其标记的 类、方法、字段，不推荐使用
    @SuppressWarning  //告知编译器忽略被该注解所标记的代码中的某些警告

注解的分类：
    源码注解： @Retention(RetentionPolicy.SOURCE)
        注解只在源码中存在，编译成.class文件就不存在了。
    编译时注解： @Retention(RetentionPolicy.CLASS)
        注解在源码和.class文件中都存在，但是在运行时不生效
    运行时注解： @Retention(RetentionPolicy.SOURCE)
        在运行阶段还起作用，甚至会影响运行逻辑的注解。

注解语法糖：
    public @interface CustomAnnotation{} 等价于 public interface CustomAnnotation extends java.lang.annotation.Annotation {}
*/
public @interface CustomAnnotation {

    //属性对应的抽象方法不能带参数，也不能声明抛出异常
    //属性的类型只能是基本类型、String、Class、枚举、注解及这些类型的数组
    String name() default "Kasei";
    int age() default 22;
    String[] str() default {"Haku", "木可川页金夆"};
}
