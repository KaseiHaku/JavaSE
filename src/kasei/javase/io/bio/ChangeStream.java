package main.java.se.io;

import java.io.*;

/**
 * 转换流：字节流转字符流     作用：处理乱码（编码集、解码集）
 * 输出流：OutputStreamWriter 编码
 * 输入流：InputStreamReader 解码
 * 
 * 乱码原因：
 * 1、编码和解码字符集不同
 * 2、字节缺少，长度丢失
 * */
class ChangeStream {
	
	public static void EnDecode() throws UnsupportedEncodingException {
		String str = "中国";//采用平台默认的字符集
		byte[] data = str.getBytes();//将字符串编码，转为二进制
		
		System.out.println(new String(data));//编码与解码的字符集相同
		System.out.println(new String(data,0,3));//字节数丢失造成乱码
		
		try {
			data = str.getBytes("utf-8");//设定编码的字符集
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(new String(data));//编码与解码的字符集不相同
		
		
		byte[] data2 = "日本".getBytes("utf-8");//将字符串“日本”用utf-8编码为二进制
		String str2 = new String(data2,"utf-8");//将二进制 data 用utf-8解码为字符串
		System.out.println(str2);	
	}
	
	public static void convertStream() throws IOException
	{
		BufferedReader br = new BufferedReader(
					new FileReader(
							new File("e:/hello.java"))//字符流无法解码要换成字节流，到br2
				);
		
//		BufferedReader br2 = new BufferedReader(
//				new FileInputStream(
//						new File("e:/hello.java")
//						)//字节流无法指定字符集，所以到br3
//				);
		
		BufferedReader br3 = new BufferedReader(
				new InputStreamReader(//添加转换流
						new FileInputStream(
								new File("e:/hello.java")),//指定字符集
						"utf-8")//乱码  原编码集不是 utf-8			
				);
		
		String info = null;
		while(null!=(info=br3.readLine()))
		{
			System.out.println(info);
		}
		br.close();
	}
	
	public static void main(String[] args)
	{
		try {
			convertStream();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
