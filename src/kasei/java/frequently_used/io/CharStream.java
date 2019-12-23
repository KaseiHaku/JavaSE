package main.java.se.io;

import java.io.*;

/**
 * 字符流：
 * 输入流：Reader  read()  close()
 * 	FileReader
 * 输出流：Writer  write()  flush()  close()
 * 	FileWriter
 * */
class CharStream 
{
	public static void txtFileReader(String str)
	{
		//1、创建源，建立联系
		File src = new File(str);
		//2、选择流
		Reader r = null;
		try 
		{
			r = new FileReader(src);
			//读取操作
			char[] buff = new char[10];
			int len =0;
			while((len=r.read(buff))!=-1)
			{
				//字符数组  转  字符串
				String str1 = new String(buff);
				System.out.println(str1);
			}		
		}catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			System.out.println("源文件不存在！");
		} catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("文件读取失败！");
		}finally
		{
			try 
			{
				if(r!=null){r.close();} 
			}catch (IOException e) 
			{
				e.printStackTrace();
				System.out.println("字符输入流关闭失败！");
			}
		}
	}

	public static void txtFileWriter(String str) 
	{
		//1、创建目标，建立联系
		File dest = new File(str);
		Writer w = null;
		try 
		{
			w = new FileWriter(dest,true);//true 表示追加文件，而不是覆盖
			String str1 = "和司法所打   \r\n ————发啊撒打发点 \r\n   速度快符号打  发去无法";
			w.write(str1);
			w.flush();
		} catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("目标文件不存在！");
		}finally
		{
			if(w!=null)
			{
				try {
					w.close();
				} catch (IOException e)
				{
					e.printStackTrace();
					System.out.println("字符输出流关闭失败！");
				}
			}
		}
	}
	
	public static void main(String[] args) 
	{	
		txtFileWriter("e:/hello.java");
		txtFileReader("e:/hello.java");
	}
}
