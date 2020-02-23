package kasei.javase.jvm.custom;

//线程上下文类加载器：为了抛弃双亲委托机制
public class ContextLoaderDemo {
	public static void main(String[] args) {
		
		//获取当前的类加载器
		ClassLoader loader = ContextLoaderDemo.class.getClassLoader();
		System.out.println(loader);
		
		//获取当前的线程上下文加载器
		ClassLoader loader2 = Thread.currentThread().getContextClassLoader();
		System.out.println(loader2);
		
		//修改当前线程上下文加载器
		Thread.currentThread().setContextClassLoader(new FileSystemClassLoader("e:/"));
		System.out.println(Thread.currentThread().getContextClassLoader());
		
	}
}
