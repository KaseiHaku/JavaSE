package kasei.javase.designpattern.structure.flyweight;

//享元模式：所使用的对象从工厂中取，工厂中有个该对象的容器，如果该对象在容器中存在则不创建，返回已有的对象，否则创建新对象放入容器中
public class Test {

	public static void main(String[] args) {
		F11_02CircleFactory cf = new F11_02CircleFactory();
		F11_01Circle circle = cf.getCircle("green");
		circle.draw();
		
		//这部分用于验证是否调用的是前面创建的对象
		F11_01Circle circle2 = cf.getCircle("green");
		circle2.draw();
	}
}
