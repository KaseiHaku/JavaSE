package kasei.javase.se.objectoriented.generic;

import java.util.List;

//非泛型类中定义泛型方法
public class GenericMethod {

	//泛型方法的定义：在返回类型前面加入<T,E,K,V>
	public static <T extends List> void print(T t){
		t.add("dfd");
	}
}
