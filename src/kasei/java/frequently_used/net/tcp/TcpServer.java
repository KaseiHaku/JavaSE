package main.java.se.net.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


class TcpServer {
	public static void main(String[] args) throws IOException {
		
		/** TCP(Socket)编程 */
		//1、创建服务器监听连接的端口  
		ServerSocket server = new ServerSocket(8888);
		
		//2、接收客户端连接  阻塞式（程序运行到这里停止，监听到连接后才往下执行）
		Socket socket = server.accept();//服务器监听一个连接后会创建一个 Socket 和客户端的 Socket绑定
		System.out.println("一个客户端建立连接");
		
		//创建服务器端输出流，并绑定节点	
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		String msg = "欢迎使用本服务器！";//设置要写的数据
		bw.write(msg);//执行
		bw.newLine();
		bw.flush();
		
		//创建服务器端输入流，并绑定节点
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String str = null;//设置读取缓冲区
		str = br.readLine();
		System.out.println("server:"+str);	
	}
}
