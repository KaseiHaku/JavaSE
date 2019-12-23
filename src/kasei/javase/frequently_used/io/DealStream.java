package main.java.se.io;

import java.io.*;

/**
 * 处理流：处理流一定在节点流之上
 * 一、字节缓冲流：
 * BufferedInputStream
 * BufferedOutputStream
 * 二、字符缓冲流：
 * BufferedReader	readLine()
 * BufferedWriter	newLine()
 * */
class DealStream
{

	public static void bufferedTxtFileReader(String str)
	{
		//1、创建源，建立联系
		File src = new File(str);
		//2、选择流
		BufferedReader r = null;//加入缓冲流
		try 
		{
			r = new BufferedReader(new FileReader(src));
			//读取操作
			char[] buff = new char[10];
			int len =0;
			while((len=r.read(buff))!=-1)
			{
				//字符数组  转  字符串
				String str1 = new String(buff);
				System.out.println(str1);
			}		
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("源文件不存在！");
		} catch (IOException e){
			e.printStackTrace();
			System.out.println("文件读取失败！");
		}finally{
			try {
				if(r!=null){r.close();} 
			}catch (IOException e) {
				e.printStackTrace();
				System.out.println("字符输入流关闭失败！");
			}
		}
	}
	
	
	public static void main(String[] args)
	{


	}

}
