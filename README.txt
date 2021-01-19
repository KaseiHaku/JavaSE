JavaSE: 是一个 jdk 中基础类的集合
JavaSE 标准目录结构

ProjectRootDirectory
        │
        ├─ lib      # 存放第三方 jar 包
        └─ src      # 存放源码




JavaEE: 是一个开发规范的集合，包含一系列的 Interface ，这些 Interface 就是开发规范，例如： Servlet，JDBC，JPA 等
JavaEE 标准目录结构

ProjectRootDirectory
        │
        ├─ src      # 存放源码
        └─ webapp   # 存放资源
            │
            ├─ WEB-INF          # 该目录下的资源不能被 URL 直接访问
            │     ├─ classes    # 保存 web 中编译好的 .class 文件
            │     ├─ lib        # 保存 web 中第三方 jar 包
            │     └─ web.xml    # web 配置文件
            └─ index.html       # 主页，这一层主要放置 web 静态资源，当前现在流行放在 WEB-INF 目录中以防止资源被直接访问




什么是 jar 包：采用 ZIP 压缩算法打包的 .class 文件的集合
jar 包目录结构：
kasei.haku-9.9.9.jar
    │
    ├─ META-INF     # 存放当前 jar 包的 元数据
    │   ├─ MANFEST.MF           # 该文件用于表明当前 jar 包的一系列属性，
    │                           # 例如：生成可执行 jar 需要指定 Main-Class:kasei.haku.MainClass 属性
    └─ kasei        # 存放编译后的 .class 文件
        └─ haku
            └─ MainClass.class



Java 相关的有用的网站：
    https://www.findjar.com         # 根据包名查找 jar 包的名字
    http://jcenter.bintray.com      # 下载 jar 包
    https://mvnrepository.com       # 下载 jar 包
    https://howtodoinjava.com/      # 好的学习网站
    http://tutorials.jenkov.com/java-nio/index.html # java nio

如何查看 Java 源代码 
    Java 实例(对象)本质上就是一个 内存块，想要理解这个内存块，就要看它一开始是怎么被构建，所以查看顺序如下
    静态语句块（类一加载就会执行） -> 动态语句块（创建类实例时执行，且在构造方法之前执行） -> 构造方法
    Java 有且只有 3 种创建实例的方法：
        1. 通过构造函数创建。该方法包括通过 new，反射
        2. 通过 clone 方法
        3. 通过反序列化创建。该方式是唯一不会调用任何构造函数的


Java 中的难点 
    1. 动态编译
    2. ClassLoader
    3. 反射
    4. 多线程

