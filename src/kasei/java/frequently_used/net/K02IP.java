package main.java.se.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/** Java 中IP地址使用的类 InetAddress */
public class K02IP {
	public static void main(String[] args) {	
		try {
			//创建InetAddress对象
			InetAddress ip1 = InetAddress.getLocalHost();//获取本机IP地址对应的对象
			InetAddress ip2 = InetAddress.getByName("www.163.com");//获取指定域名对应的IP地址对象			
			InetAddress ip3 = InetAddress.getByName("233.87.1.58");//获取指定IP对应的IP地址对象
			
			System.out.println("本地主机IP地址："+ip1.getHostAddress());
			System.out.println("本地主机主机名（如果没有域名就显示IP地址）："+ip1.getHostName());
		} catch (UnknownHostException e){
			e.printStackTrace();
		}
	}
}
