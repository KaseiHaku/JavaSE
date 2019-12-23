package kasei.javase.designpattern.establish.factory;

//工厂模式:就是其他类的实例由工厂类的方法返回
public class ShapeFactory {
	public Shape getShape(String shapeType) {
		if(shapeType.equalsIgnoreCase("circle")){ 
			return new Circle();
		}
		if(shapeType.equalsIgnoreCase("rectangle")){ 
			return new Rectangle();
		}
		return null;		
	}
}
