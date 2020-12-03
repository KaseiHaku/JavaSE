package kasei.javase.designpattern.establish.factory;



/**
    jar 包 A 有 interfaceA 和 ClassA1...ClassA100 的实现类，
    jar 包 B 依赖 A，如果没有 工厂类 那么 B 中必然会存在对实现类的引用，有了 工厂类 B 只需要引用 interfaceA 就行了
*/
public class Test {
	
	public static void main(String[] args) {
		
		ShapeFactory shapeFactory = new ShapeFactory();
		Shape shape = shapeFactory.getShape("circle");
		shape.whatAmI();
		
		shape = shapeFactory.getShape("Rectangle");
		shape.whatAmI();
	}
}
