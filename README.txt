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
        ├─ lib      # 存放第三方 jar 包
        ├─ src      # 存放源码
        └─ web      # 存放资源
            │
            ├─ WEB-INF          # 该目录下的资源不能被 URL 直接访问
            │     ├─ classes    # 保存 web 中编译好的 .class 文件
            │     └─ web.xml    # web 配置文件
            └─ index.html       # 主页，这一层主要放置 web 静态资源，当前现在流行放在 WEB-INF 目录中以防止资源被直接访问