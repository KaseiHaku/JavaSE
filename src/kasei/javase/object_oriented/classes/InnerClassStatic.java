package kasei.javase.se.objectoriented.classes;

public class InnerClassStatic {
	
	//静态内部类:编译后会独立生成一个class文件，文件名为：OuterClass$StaticNestedClass.class（多重嵌套，以此类推）
    //创建静态嵌套类对象的语法：
    //OuterClass.StaticNestedClass  nestedObj = new OuterClass.StaticNestedClass();
	public static class Yy{
		//1、静态嵌套类只能访问外部类的静态成员
		//2、另一个类中创建内部类对象语法：：OuterClass.StaticNestedClass  nestedObj = new OuterClass.StaticNestedClass();
		void say(){ System.out.println("静态内部类 Yy");}
	}
}
