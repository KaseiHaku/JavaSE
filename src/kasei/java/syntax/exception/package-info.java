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


