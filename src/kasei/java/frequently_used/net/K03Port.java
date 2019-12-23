package main.java.se.net;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/** Java 中端口对应的类 InetSocketAddress */
public class K03Port {
	public static void main(String[] args) throws UnknownHostException {
		//创建对象
		InetAddress ip1 = InetAddress.getByName("www.baidu.com");
		InetSocketAddress port1 = new InetSocketAddress(ip1,9999);		
		InetSocketAddress port2 = new InetSocketAddress("127.0.0.1", 6765);
		
		System.out.println("当前端口对象对应的主机名（如果没有域名就显示IP地址）："+port1.getHostName());
		System.out.println("当前端口对象对应的端口："+port1.getPort());
		
		InetAddress ip2 = port2.getAddress();//获取InetAddress对象
		System.out.println(ip2.getHostAddress());
	}
}
