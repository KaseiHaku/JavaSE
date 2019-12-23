package main.java.se.net.tcp;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

class TcpClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		//1、创建客户端  必须指定服务器+端口  此时就建立连接
		Socket client = new Socket("127.0.0.1", 8888);
		
		//创建客户端输入流，并绑定节点
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String echo = br.readLine();//execute read
		System.out.println("client:"+echo);
		
		//创建客户端输出流，并绑定节点
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		String str = "窝囊废！\r\n";//设置需要 write 的 data
		bw.write(str);//execute write
		bw.close();//这行代码用于保证客户端socket被虚拟机回收之前，流已经执行完毕，否则报错
	}
}
