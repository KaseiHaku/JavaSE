package kasei.javase.designpattern.establish.f02AbstractFactory;

//抽象工厂模式：工厂的工厂
public abstract class AbstractFactory {
	public abstract Color getColor(String colorType);
	public abstract Shape getShape(String shapeType);
}
