package kasei.javase.designpattern.establish.f02AbstractFactory;

public class ColorFactory extends AbstractFactory {

	@Override
	public Color getColor(String colorType) {
		if(colorType == null){
			return null;
		}else if(colorType.equalsIgnoreCase("red")){
			return new Red();
		}else if(colorType.equalsIgnoreCase("green")){
			return new Green();
		}else{
			return null;
		}
	}

	@Override
	public Shape getShape(String shapeType) {
		return null;
	}

}
