package kasei.javase.se.objectoriented.classes;

import java.util.Date;
import static kasei.utility.StringUtil.*; // 表示导入 StringUtil 类中所有的静态方法
import static kasei.utility.StringUtil.trim; // 静态导入能导入一个类中的静态字段、方法

public class NormalClass {
    /***************************** 访问修饰符：（竖线是对齐的） *******************************
     *              类       字段      方法|         包外      子类      包内      类内
     * public       k       k           k|          o       o           o           o
     * protected            k           k|                  o           o           o
     * default      k       k           k|                              o           o	
     * private              k           k|                                          o
     * */
	
	/*** 类加载及创建对象内存中内容的先后顺序(静态成员变量，静态方法，静态语句块，动态成员变量，动态方法，动态语句块) ********/
	/************************************* 静态成员变量、静态语句块、静态方法  **************************************/
	public static String[][] clothes = new String[2][];//静态成员变量：可以通过类名直接访问
	static {//静态语句块：当类被加载时，JVM会自动执行语句块中的代码，这对于想在类的加载阶段做一些复杂的初始化操作非常有用

		clothes[0] = new String[2];
		clothes[1] = new String[1];
		clothes[0][0] = "金丝雀袍";
		clothes[0][1] = "红菱歌姬";
		clothes[1][0] = "三妈式";
		System.out.println("static 语句块执行...");
	}
	/* 静态方法
	 * 1、直接通过类名来访问，无须先创建对象。
	 * 2、在类被加载后（创建对象前），它们就已经初始化了，无论创建多少个对象，静态变量（静态方法）只有一份，被所有对象共享
	 * 3、静态方法只能访问类的静态变量
	 * */	
	static void getCloth(){//静态方法：静态方法只能访问类的静态变量，有且只有一份	
		System.out.println("类方法："+ NormalClass.clothes[0][0]);//访问类的静态变量（类的非静态变量必须通过对象访问）
	}
	
	
	
	/******************************* 普通字段、动态语句块、构造函数  **************************************/
	//初始化字段的方法：1、直接赋值法  2、构造方法  3、静态语句块(值初始化 static 字段)  4、非静态语句块
	public String name = "Haku";//普通成员变量：需要先创建实例才能访问
	public char cup = 'C';
	public final String HAIR_COLOR = "purple";//最终变量：定义时必须初始化，一旦赋值不能修改，变量名全大写，单词间 ‘_’分隔
	public final Date date = new Date();//表示 PROTO 这个引用不能指向其他对象,只能指向当前创建的对象，但是所创建的实例本身是可以改变的	

	
	{	//动态语句块，在构造函数之前执行此部分代码，每次创建实例都执行该部分代码	
		cup = 'E';	
		{
			//语句块可以嵌套
			//语句块内部声明变量跟外部变量不能重名，并列语句块变量可以重名
			//语句块内部可以访问上层的外部变量	
		}
		System.out.println("dynamic 语句块执行...");
	}
	
	/* 构造方法注意点：
	 * 1、如果没有定义构造方法系统自动添加一个无参构造方法，如果定义了构造方法，则系统就不会自动添加无参构造方法
	 * 2、构造方法的第一句必须写 super();即调用直接父类的构造方法;不写是默认调用父类的无参构造方法
	 */
	public NormalClass(char cup) {//构造方法1：类中所有的构造函数 都隐式的指定为 static

		super();//调用Object 根类的无参构造方法
		this.cup = cup; //this：表示正在创建的对象的引用，只能用于方法内部，且不能用于静态方法
		System.out.println("constructor1 执行...");
	}
	public NormalClass(String name, char cup) {//构造方法2

		this(cup);//this：在构造方法中调用类自身的其他构造方法
		this.name = name;//this：调用自身成员变量或函数
		System.out.println("constructor2 执行...");
	}	
	
	
	/************************************* 普通方法  **************************************/	
	final void finalFunction(){}//最终方法：方法不能被子类重定义，类中所有的 private 方法都隐式的指定为 final
	void funv(final Date date)	{//final参数：表示无法在方法内部修改 str 引用所指向的对象
		final int i;//方法内部变量只能用final修饰，当然可以不修饰
		//date = new Date();//出错：不能修改 final 参数所指向的实例
		date.setTime(32434);//可以改变 final参数指向实例的内容
	}	
	
	int funv(int i){
		return i+1;
	}
}
