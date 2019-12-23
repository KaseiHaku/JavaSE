package kasei.javase.se.objectoriented.inherit;

/* 继承的初始化全过程：
 * 假设 C 继承自  B ，B 又继承自 A，C c = new C(); 这条语句的全过程
 * 0、在任何其他事物发生之前，将分配给对象的存储空间初始化成 二进制零
 * 1、类加载器，加载C(发现有 extends)-->加载B-->加载A
 * 		-->执行A的 static（静态变量及语句块，按声明顺序执行） -->执行B的 static-->执行C的 static
 * 		-->执行A的非static（变量及语句块，按声明顺序执行）-->执行A的构造函数
 * 		-->执行B的非static-->执行B的构造函数
 * 		-->执行C的非static-->执行C的构造函数
 * */

/* 继承内存分析：
 * 创建子类对象时，会先创建父类实例，然后创建子类扩展的东西，和父类实例包含在一起形成子类实例
 * */

import kasei.javase.se.objectoriented.inherit.other.K08A;

/* 继承注意点：
 * 1、子类拥有父类的 public 和 protected 修饰的字段和方法，
 *    默认权限的字段和方法，只有子类和父类在同一个包中，子类才会拥有他们
 * 2、子类不会自动拥有父类的构造方法
 *
 * */
public class K08Haku extends K08Miku implements K08C
{
	//super 的用法: super 关键字不能出现在静态方法或静态语句块中
	K08Haku()//super();//super 表示直接父类的引用:super() 调用父类的构造方法
	{
		super("07");//调用 父类构造方法，该语句必须作为构造方法的第一条语句
	}
	
	String typeNum = "11";
	public void useSuper()
	{
		System.out.println(super.typeNum);//在子类中访问父类的字段或方法
	}
	

	//实现接口 InerfaceC 中的抽象函数
	public void myName(){
		System.out.println("my name is 洛天依   " + PI +" "+ K08A.STR);//调用指定接口的字段
	}
	
	public double fun(String str){
		System.out.println(str);
		return Double.parseDouble(str);
	}
}
