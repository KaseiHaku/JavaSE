package main.java.se.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式在Java中的使用
 * */
class A 
{
	public static void main(String[] args) 
	{
		//创建正则表达式的实例
		Pattern p = Pattern.compile("\\w+");
		
		//创建 Matcher 实例
		Matcher m = p.matcher("asfsd%%23323");
		
		boolean result = m.matches();//匹配整个字符序列
		System.out.println(result);
		
		while(m.find())//查找有没有下一个子序列
		{
			System.out.println(m.group());//返回查找到的序列
		}
		//替换
		
		//分割
	}
}
