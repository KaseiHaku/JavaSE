package kasei.javase.jvm.classloader;

/** todo Java 类加载器
什么是 ClassLoader：
    类加载器是用于加载 java 程序中所有使用到的资源（包括 .properties 等配置文件）的类

Java 类加载器的层次结构：
    BootstrapClassLoader(启动类加载器，C++ 代码实现，java 中显示为 null)
        └─ sun.misc.Launcher$ExtClassLoader(扩展类加载器)
            └─ sun.misc.Launcher$AppClassLoader(应用程序类加载器)
                └─ UserClassLoader(用户自定义类加载器)
                           
各个类加载器解释：
    Bootstrap: 启动类加载器
        1. 使用原生代码实现的，并不继承自java.lang.ClassLoader.
        2. Bootstrap 类不用专门的类加载器加载
        3. Bootstrap 类负责加载 JAVA_HOME/jre/lib/rt.jar 或 sun.boot.class.path 路径下的内容
        4. 加载扩展类和应用程序类加载器，并制定他们的父类加载器
        5. 由 Bootstrap 类加载器加载的类 Class.getClassLoader() 方法返回 null
 
    sun.misc.Launcher$ExtClassLoader: 扩展类加载器
        1. 加载 JAVA_HOME/jre/ext/*.jar 或 java.ext.dirs 路径下的内容
        2. 该类加载器类全路径：
 
    sun.misc.Launcher$AppClassLoader: 应用程序类加载器
        1. 加载 java 应用的类路径（classpath，java.class.path 路径下的类），
        2. 一般来说，java应用的类都是由它加载的
 
    UserClassLoader：开发人员可以通过继承java.lang.ClassLoader类的方式实现自己的类加载器，以满足一些特殊的需求

    ThreadContextClassLoader：线程上下文类加载器，不是一个实际的加载器，而是指一种类加载器角色
        jvm 启动时，创建 main 线程时，自动设置 main 线程的 ThreadContextClassLoader 为 Launcher$AppClassLoader 
        创建子线程时，如果没有设置 ThreadContextClassLoader，那么会从父线程中继承一个


JVM 中类命名空间定义
    定义：
        1. 一个类在 JVM 中的唯一性标识 = 初始类加载器 + 全类名; 如果同一个类被不同的 类加载器 加载，那 jvm 认为是不相同的类
        2. 一个类 A 加载过程中，返回链中包含类 A.class 引用的 类加载器成为 InitiatingLoader(初始加载器)
        3. 一个类 A 加载过程中，调用了 defineClass 方法的类加载器，称为类 A 的 DefiningLoader(定义加载器)
        

InitiatingLoader & DefiningLoader:
    双亲委托机制（即先由上层加载器加载，如果上层加载不了才使用子层加载器加载）
    
    1. java 代码中第一次调用 new , 调用静态方法或字段 会触发类加载
    2. Launcher$AppClassLoader 委托 Launcher$ExtClassLoader
    3. Launcher$ExtClassLoader 委托 BootstrapClassLoader
    4. BootstrapClassLoader 实际加载类，如果加载不到，则逐步逆向返回
    

类加载过程：
    java.lang.String 类加载过程
        首次调用 new String(); String.join(); String.class; 等代码，会触发类加载，默认使用当前类的 ClassLoader, 一般就是 AppClassLoader
        AppClassLoader 调用 loadClass 方法，委托给 ExtClassLoader 加载
        ExtClassLoader 调用 loadClass 方法，委托给 BootstrapClassLoader 加载
        BootstrapClassLoader 调用 loadClass 方法，不再委托，调用 defineClass 方法，执行实际加载动作；
        
        返回链开始：
        BootstrapClassLoader 返回一个 String.class 实例给 ExtClassLoader 
        ExtClassLoader 返回接收到的 String.class 实例给 AppClassLoader
        AppClassLoader 返回接收到的 String.class 给 javacode
        
        所以 String 类有 3 个 initiating loader，分别为 BootstrapClassLoader,AppClassLoader,ExtClassLoader
        有 1 个 defining loader，为 BootstrapClassLoader
        所以 String 类，存在于 AppClassLoader,ExtClassLoader,BootstrapClassLoader 这 3 个类加载器的命名空间中

    kasei.Custom 类加载过程
        javacode 触发类加载，默认使用 AppClassLoader 加载，
        AppClassLoader 调用 loadClass 方法，委托给 ExtClassLoader
        ExtClassLoader 调用 loadClass 方法，委托给 BootstrapClassLoader
        
        返回链开始：
        BootstrapClassLoader 没有找到类，返回 null 给 ExtClassLoader 加载     // 没有返回 Custom.class 的实例
        ExtClassLoader 没有找到类，返回 null 给 AppClassLoader 加载           // 没有返回 Custom.class 的实例
        AppClassLoader 找到了类，调用 defineClass 执行加载动作，并返回 Custom.class 给 javacode  // 有 Custom.class 的实例
        
        所以 Custom 类只有 1 个 initiating loader，为 AppClassLoader
        有 1 个 defining loader，为 AppClassLoader
        所以 Custom 类，只存在于 AppClassLoader 这个类加载器的命名空间中
    
    java.sql.Driver 类加载过程（ThreadContextClassLoader）
        javacode 触发类加载
        AppClassLoader 调用 loadClass 方法，委托给 ExtClassLoader
        ExtClassLoader 调用 loadClass 方法，委托给 BootstrapClassLoader
        BootstrapClassLoader 会调用当前配置的 ThreadContextClassLoader 去加载类
        
        Driver 使用 ThreadContextClassLoader 加载，
        所以 Driver 类只有 1 个 initiating loader，即加载时配置的 ThreadContextClassLoader （默认为 AppClassLoader），
        也只有 1 个 defining loader，也为加载时配置的 ThreadContextClassLoader
    
    基于以上 initiating loader ，
        Custom 类中可以访问 String 类，反之则不行
        

破坏双亲委托机制：
    第一种：重写 java.lang.ClassLoader 类的 loadClass() 方法
    第二种：Thread.currentThread().getContextClassLoader().loadClass("kasei.Haku");
    第三种：OSGi(Open Service Gateway Initiative)

*/


