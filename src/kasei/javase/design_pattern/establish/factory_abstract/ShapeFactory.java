package kasei.javase.designpattern.establish.f02AbstractFactory;

public class ShapeFactory extends AbstractFactory {

	@Override
	public Color getColor(String colorType) {
		return null;
	}

	@Override
	public Shape getShape(String shapeType) {
		if(shapeType == null){
			return null;
		}else if(shapeType.equalsIgnoreCase("circle")){
			return new Circle();
		}else if(shapeType.equalsIgnoreCase("rectangle")){
			return new Rectangle();
		}else{
			return null;
		}
	}
}