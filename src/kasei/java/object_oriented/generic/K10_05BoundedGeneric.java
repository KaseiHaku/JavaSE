package kasei.javase.se.objectoriented.generic;

import java.util.List;

public class K10_05BoundedGeneric {
	
	//有界泛型用于函数参数的声明
	public void testExtends(List<? extends A> list){
		list.add(null);
		//list.add(new A());//上界泛型不能添加任何实例，因为无法确定到底添加的是哪个类型，除了null
		//list.add(new AA());
		//list.add(new AAA());
		
		list.get(1).printA();//上界泛型可以调用父类的方法，因为任何子类可以造型为父类
	}
	
	public void testSuper(List<? super AA> list){
		list.add(null);
		//list.add(new A());//下界泛型不能添加父类及以上实例，因为无法确定到底是哪个类型
		list.add(new AA());
		list.add(new AAA());//下界泛型能添加本身及其子类的实例，因为子类都可以向上造型为父类引用，类型明确，所以可以
		
		//list.get(1).printA();//下界泛型不能调用方法，因为父类不能造型为子类，所以类型不明确，因此不能使用
	}	
}

class A{
	public void printA(){}
}

class AA extends A {
	public void printAA(){}
}

class AAA extends AA {
	public void printAAA(){}
}