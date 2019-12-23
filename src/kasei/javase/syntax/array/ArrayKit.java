package kasei.javase.se.syntax.array;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Java 里面数组是数组，字符串是字符串，两者 tm 的没联系，别 tm 还以为在学 C 
 * */
class ArrayKit {

	public static void main(String[] args) {
		char[] chars1 = {'a','c','f','d','e','b','g','h'};
		char[] chars2 = new char[10];
		System.out.println("该数组长度："+ chars1.length);
		
		byte[] bytes = null;
		
		System.arraycopy(chars1, 3, chars2, 5, 4);//复制数组
		System.out.println("复制数组指定范围的数据："+ Arrays.toString(chars2));
		
		Arrays.sort(chars1);//对数组从小到大排序
		System.out.println("数组排序："+ Arrays.toString(chars1));
		System.out.println("数组转字符串："+ Arrays.toString(chars1));
		
		//二分法查找指定元素是否在数组内前提是数组有序，必须先用Arrays.sort()进行排序才能用
		System.out.println("二分查找指定元素是否在素组内（负数表明不存在）："+ Arrays.binarySearch(chars1, 'b'));
		
		Arrays.fill(chars1, 2, 6, '*');//向数组指定位置填充指定元素
		System.out.println("向数组指定位置填充指定元素:"+ Arrays.toString(chars1));
		
		int[] ints = {0,1,2,3,4,5,6};
		IntStream  s = Arrays.stream(ints);//将指定数组转化成流
	}
}
