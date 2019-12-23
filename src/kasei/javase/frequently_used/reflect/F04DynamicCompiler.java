package kasei.javase.se.frequently.reflect;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.net.URL;
import java.net.URLClassLoader;

/** 动态编译 */
public class F04DynamicCompiler {
	public static void main(String[] args) 
	{
		String str = "public class HelloWorld{public static void main(String[] args){System.out.println(\"Hi!\");}}";
		
		/** 编译文件中的java代码 */
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		//先在E盘下建一个HelloWorld.java文件
		int result = compiler.run(null, null, null, "E:\\HelloWorld.java");
		System.out.println(result==0?"编译成功":"编译失败");
		
		
		/** 编译内存中的java代码 */
		
		
		//通过Runtime.getRuntime();启动新进程运行
//		Runtime run = Runtime.getRuntime();
//		try 
//		{
//			Process process = run.exec("java -cp E:/aa/  HelloWorld");
//			
//			
//			
//			InputStream in = process.getInputStream();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//			String info = "";
//		
//			while((info=reader.readLine())!=null)
//			{
//				System.out.println(info);
//			}
//		} 
//		catch (IOException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		//通过反射运行
		try
		{
			URL[] urls =new URL[]{new URL("file:/"+"E:/")};
			URLClassLoader loader = new URLClassLoader(urls);
			Class cls = loader.loadClass("HelloWorld");
			//调用加载类的main方法
			cls.getMethod("main",String[].class).invoke(null, (Object)new String[]{});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
