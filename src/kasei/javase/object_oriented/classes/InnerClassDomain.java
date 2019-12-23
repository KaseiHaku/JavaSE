package kasei.javase.se.objectoriented.classes;

public class InnerClassDomain {
	
	public void fun(){
		final int i =7;
		//局部内部类
		class Gg{
			//1、只在其所在的方法内部可见
			//2、局部内部类中不能访问其所在方法的成员（ final 成员除外），但能访问外部类所有成员
			void print(){ System.out.println("局部内部类访问其所在方法的 final 局部变量 i =" + i);}
		}
		Gg gg = new Gg();
		gg.print();
	}
}
