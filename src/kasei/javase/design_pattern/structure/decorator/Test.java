package kasei.javase.designpattern.structure.decorator;

//装饰器模式：即通过在装饰器类D中组合一个被装饰类A，装饰器D类，用构造函数传入一个A类，并在D类中重写A类中的方法
public class Test {

	public static void main(String[] args) {
		Shape shape = new Circle();
		ShapeDecorator decorator = new RedShapeDecorator(shape);
		decorator.draw();
	}
}
