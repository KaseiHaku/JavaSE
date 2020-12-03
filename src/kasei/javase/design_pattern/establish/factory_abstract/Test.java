package kasei.javase.designpattern.establish.f02AbstractFactory;



/* 抽象工厂 跟 工厂 的区别
工厂： 给个 参数 直接获取到 产品实例
抽象工厂： 给个 参数  获取到的是  工厂的实例，  再给工厂实例一个参数，才能获取 产品实例


*/

//抽象工厂模式
public class Test {
	public static void main(String[] args){
		
		FactoryProducer fp = new FactoryProducer();		
		AbstractFactory af = fp.getFactory("shapeFactory");
		Shape shape = af.getShape("circle");
		shape.draw();
		shape = af.getShape("rectangle");
		shape.draw();
		
		af = fp.getFactory("colorFactory");
		Color color = af.getColor("green");
		color.print();
		color = af.getColor("red");
		color.print();		
	}
}
