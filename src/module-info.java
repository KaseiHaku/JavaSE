/**
 * JDK Accessibility:
 *  jdk8: public protected package private
 *  jdk9: public to everyone
 *        public but only to friend modules
 *        public only within a module
 *        protected
 *        package
 *        private
 *
 * Module Type:
 *  named module:
 *      具有 module-info.java 文件的 module
 *      效果：
 *          不允许访问 未命名模块 的类
 *  unnamed module:
 *      所有 --class-path 下的 jar 文件自动转为 未命名模块
 *      效果等价于 named module 的：
 *          exports 所有包
 *          requires 所有模块
 *          split package(即：一个包路径同时存在与两个 module 中), 只有在 unnamed module 中才不会报错
 *
 *  automatic module:
 *      把一个未经模块化改造的 jar 文件放在 --module-path 路径中，则会转为 自动模块，
 *      自动模块也属于命名模块的范畴，其名称是模块系统基于 jar 文件名自动推导得出的，
 *      比如 com.foo.bar-1.0.0.jar 文件推导得出的自动模块名是 com.foo.bar
 *      效果等价于 named module 的：
 *          exports 所有包
 *          requires 所有命名模块
 *          允许访问所有 未命名模块 的类
 *          多个 自动模块 不能导出同一个包，但是 未命名模块 可以
 *
 * Modular execute main:
 *  # 完全没有模块化时，项目的启动命令
 *  shell> java -cp mod1.jar:mod2.jar:mod3.jar:mod4.jar xxx.yyy.MainClass
 *
 *  # mod3, mod4 模块化后，项目的启动命令，
 *  # --add-modules 的作用：
 *  #   添加指定的模块为 root module，用于构建模块系统的依赖图谱
 *  #   如果是 shell> java -m mod 方式启动 mod 就是 root module
 *  #   如果是 shell> java xxx.yyy.MainClass 方式启动，那么 root module 是所有的 java.* 模块(即：JRE)  和 classpath 下的模块
 *  #   --add-modules 是手动添加额外的 root module，否则会报 ClassNotFoundException
 *  shell> java -cp mod1.jar:mod2.jar -p mod3.jar:mod4.jar --add-modules mod3,mod4 xxx.yyy.MainClass
 *
 *  # 所有都模块化后，项目的启动命令
 *  shell> java -p mod1.jar:mod2.jar:mod3.jar:mod4.jar -m mod1/xxx.yyy.MainClass
 *
 * */
import kasei.springcloud.basic.common.spi.KaseiClassFileTransformerSpi;
import kasei.springcloud.basic.common.spi.impl.Neo4jKaseiClassFileTransformSpi;

/**
 * open 表示模块内的所有包都允许通过 Java 反射访问，模块声明体内不再允许使用 opens 语句
 * */
open module kasei.springcloud.basic.common {
    /**
     * requires 表示当前模块依赖的其他模块，一次只能声明一个依赖，可以多次声明
     * transitive 传递依赖，如果 A 模块依赖 B 模块，B 模块依赖 C 模块，那么该关键字修饰时，即 A 模块自动依赖 C 模块
     * */
    requires transitive spring.core;
    /**
     * exports 导出当前模块内的包，供其他模块直接 import 使用，一次只能导出一个包，可以多次声明
     * to 定向导出，导出的包只给指定的模块直接 import
     * */
    exports kasei.springcloud.basic.common.bean to kasei.springcloud.basic.file,
        kasei.springcloud.basic.security, kasei.springcloud.basic.notice;

    /**
     * opens 开放当前模块内的包，允许其他模块通过 java 反射访问，一次只能导出一个包，可以多次声明
     * to 定向开放，开放的包只给指定的模块通过 java 反射访问
     * */
    // opens kasei.springcloud.basic.common to kasei.springcloud.basic.file,
    //     kasei.springcloud.basic.security, kasei.springcloud.basic.notice;

    /**
     * provides 声明当前模块提供的 Java SPI 服务，一次可以声明多个服务实现类（逗号分隔）
     * */
    // provides <interface | abstract class> with <class1>[, <class2> ...];
    provides KaseiClassFileTransformerSpi with Neo4jKaseiClassFileTransformSpi;

    /**
     * uses 声明当前模块依赖的 Java SPI 服务，加上之后模块内的代码就可以通过 ServiceLoader.load(Class) 一次性加载所声明的 SPI 服务的所有实现类
     */
    // uses <interface | abstract class>;

}
