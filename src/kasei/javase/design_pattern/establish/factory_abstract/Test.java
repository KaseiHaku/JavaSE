package kasei.javase.designpattern.establish.f02AbstractFactory;


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
