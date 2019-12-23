package main.java.se.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/** Java 中 URL对应的类  URL*/
public class K04URL {

	public static void testURL() throws MalformedURLException{
		
		URL url1 = new URL("http://www.baidu.com:80/index.html#anchor?username=ksf");//绝对路径构建	
		System.out.println("协议："+url1.getProtocol());
		System.out.println("域名："+url1.getHost());
		System.out.println("端口："+url1.getPort());
		System.out.println("资源："+url1.getFile());
		System.out.println("相对路径资源："+url1.getPath());
		System.out.println("锚点："+url1.getRef());
		System.out.println("参数(存在锚点返回null，如果不存在则正确显示)："+url1.getQuery());
		
		
		URL url2 = new URL("http://www.baidu.com:80/a/");
		URL url3 = new URL(url2,"b.txt");//相对路径构建
		System.out.println("相对路径构建URL："+url3.toString());	
	}
	
	public static void getURLResource() throws IOException{
		URL url = new URL("http://www.baidu.com");
		
		
		InputStream is = url.openStream();//获取网络流
		byte[] buffer = new byte[1024];
		int len= 0;
		while(-1!=(len=is.read(buffer))){
			System.out.println(new String(buffer, 0, len));
		}
		is.close();
		
		
/*		BufferedReader br= 
				new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		String msg = null;
		while(null!=(msg=br.readLine())){
			System.out.println(msg);
		}*/
	}
	
	public static void main(String[] args) throws IOException{
		//testURL();   //测试URL对象常规使用方法
		getURLResource(); //测试从URL中获取资源
	}
}
