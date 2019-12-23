package main.java.jvm;


/****************** OSGI (open service gateway initative) 面向java的动态模块系统 ***********
 * 作用：向开发人员提供一个面向服务和基于组件的运行平台，开发人员可以开发各种插件来丰富这个平台，Eclipse就是基于OSGI技术构建的
 * 原理：每个插件都有自己独立的类加载器，如果需要加载其他的插件的类，需要调用其他插件的类加载器加载，
 *     与Tomcat不同，插件各自的类加载器可以相互调用
 * */
public class K08OSGI {

}
