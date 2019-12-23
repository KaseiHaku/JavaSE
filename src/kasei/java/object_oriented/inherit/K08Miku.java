package kasei.javase.se.objectoriented.inherit;

public class K08Miku extends K08Vocaloid
{
	//继承抽象类，需要实现抽象类中所有 abstract 方法，并且方法的访问权限 >= 原权限
	public void myName(){
		System.out.println("My Name is " + this.name);
	}
	
	//用于子类展示 super 的用法
	String typeNum;	
	public K08Miku(String typeNum) 
	{
		super();
		this.typeNum = typeNum;
	}
	
	//继承中的清理
	void cleanUp()
	{ 
		System.out.println("clean up Miku"); 
	}
}
