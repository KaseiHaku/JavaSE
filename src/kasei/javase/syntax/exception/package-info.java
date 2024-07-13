package kasei.javase.se.syntax.exception;

/** Java 异常层次结构:********************************************************
 * Throwable
 *  │
 *  ├─ Error                            (Error 类及其子类，程序员无法处理，也不必处理)
 *  └─ Exception                        (Checked 类异常，必须处理)
 *        ├─ Exception及其子类           (Checked 类异常，必须处理，外部错误，比如文件读取失败、网络连接错误等)
 *        └─ RuntimeException及其子类    (Unchecked 类异常，处不处理均可，如果出现该类型异常，那么一定是程序员代码写错了)
 * */

/** Java throws 关键字的使用
 * 1. 被调函数如果 throws 异常之后，调用函数才能捕获；
 * 2. throws 关键字可以抛出 Error, Exception, RuntimeException 异常，即所有 Error, Checked 和 Unchecked 异常
 * Checked 和 Unchecked 异常的使用场景
 * 1. Checked: 代码错误，抛出该类型的异常，如果强制要求调用者必须处理
 * 2. Unchecked：代码正确，但是遭遇到不可预测异常
 * */


/*
Java 异常设计原则：
    异常需要包含的信息：
        什么出错了？
        哪里出错了？
        为什么出错了？
    
    异常处理原则：
        具体明确：使用自定义的异常，明确指定什么出错了
        尽早抛出：在可能出现异常的地方直接抛出异常，而不是等执行出错了才抛异常，比如方法参数，应该先校验，校验出错直接抛异常，而不是等程序执行出错
        延迟捕获：不要在无法恢复异常的地方捕获异常，以下是两个适合捕获异常的层面
            1. 可以从异常中有意义地恢复并继续下去，比如：没有参数，则使用默认参数
            2. 能够为用户提供明确的信息，包括引导他们从错误中恢复过来，比如：给用户提示哪里出错
*/

/**
 * 常见异常: 
 *    java.lang.ClassNotFoundException        # 编译时，classpath 中找到不到类
 *    java.lang.NoClassDefFoundError          # 运行时，classpath 中找不到类
 * */


