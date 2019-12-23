package kasei.javase.se.objectoriented.classes;

import kasei.javase.se.objectoriented.classes.other.InterfaceC;

public class InnerClassAnonymous {

	//匿名内部类
    public static InterfaceC fun3() {

		final int x = 3;
		/* 匿名内部类：与普通类没有区别（一般用于方法的参数）
		 * 匿名内部类定义：new 父类构造器(参数表)实现接口(){}
		 * 若程序只需要创建类的一个对象，且该类需要继承父类（或实现父接口）时，可以考虑使用匿名内部类
		 * */
		return new InterfaceC(){
					//通常是重写父类（或父接口）方法
					//在匿名内部类中使用一个在其外部定义的对象，那么该对象必须是 final 型
					public int t ;

					@Override
					public void printface(){
						System.out.println("匿名内部类" + x);
					}
				};//这段代码仅仅是个表达式（函数调用）！
	}
}
