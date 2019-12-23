package kasei.javase.designpattern.establish.f02AbstractFactory;

//工厂的工厂
public class FactoryProducer {
	public AbstractFactory getFactory(String choice){	   
        if(choice.equalsIgnoreCase("shapeFactory")){
			return new ShapeFactory();
		}else if(choice.equalsIgnoreCase("colorFactory")){
			return new ColorFactory();
		}
		return null;
    }
}
