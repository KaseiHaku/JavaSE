package kasei.javase.designpattern.establish.factory;

public class Test {
	
	public static void main(String[] args) {
		
		ShapeFactory shapeFactory = new ShapeFactory();
		Shape shape = shapeFactory.getShape("circle");
		shape.whatAmI();
		
		shape = shapeFactory.getShape("Rectangle");
		shape.whatAmI();
	}
}
