package main.java.jvm;

import java.io.FileNotFoundException;
import java.net.URL;

/** 什么是类加载器：
 * 在 java 中，所有运行时需要的资源都通过类加载器加载到 kasei.javase.jvm 中
 * */
public class ClassLoaderIntroduce {

	// 获取 ClassLoader 的方法
	public void fetchClassLoader() {
			
		// 方法一：获取当前类的ClassLoader
		ClassLoader classLoader1 = this.getClass().getClassLoader();
		
		// 方法二:获取当前线程的ClassLoader  
		ClassLoader classLoader2 = Thread.currentThread().getContextClassLoader();
		
		// 方法三：获取当前项目的 ClassLoader，即系统的入口点所使用的 ClassLoader。
		ClassLoader classLoader3 = ClassLoader.getSystemClassLoader();
		
		System.out.println("当前类的 ClassLoader = "+classLoader1);
		System.out.println("当前线程的 ClassLoader =  "+classLoader2);
		System.out.println("系统的 ClassLoader = "+classLoader3);
	}
	
	
	// 文件的载入方式
	public void loadFile() throws FileNotFoundException {
		
		// 获取路径，方法1：获取类加载的根路径
		String projectPath = this.getClass().getResource("/").getPath();
		System.out.println(projectPath);
		
		// 获取路径，方法2：获取当前类的加载目录
		String classPath = this.getClass().getResource("").getPath();		
		System.out.println(classPath);
		
		// 获取路径，方法3：
		URL xmlPath = this.getClass().getClassLoader().getResource("");
		System.out.println(xmlPath);
		
		// 获取路径，方法4：获取当前工程的路径
		System.out.println(System.getProperty("user.dir"));
		
		//  获取路径，方法5： 获取所有的类路径 包括jar包的路径
        System.out.println(System.getProperty("java.class.path"));
		
		// 方法一：直接用 io 流读取
        //File f = new File("com/alexia/config/sys.properties"); // 使用相对路径  
        //InputStream is1 = new FileInputStream(f);   
		
		// 方法二：使用 ClassLoader
        //InputStream is2 = this.getClass().getClassLoader().getResourceAsStream(  
               //"com/alexia/config/sys.properties");
		
		// 方法三：使用ResourceBundle 
        //ResourceBundle bundle = ResourceBundle.getBundle("com.alexia.config.sys");   
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		ClassLoaderIntroduce gg = new ClassLoaderIntroduce();
		gg.fetchClassLoader();
		System.out.println("===================================================");
		
		gg.loadFile();
		System.out.println("===================================================");
		
		// 如果一个类是通过bootstrap 载入的，那我们通过这个类去获得classloader的话，有些jdk的实现是会返回一个null的
		System.out.println(Object.class.getClassLoader());
	}
}
