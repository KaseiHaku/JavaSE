package kasei.javase.designpattern.structure.bridge;

//桥接模式：就是在一个父类中组合一个借口类，使用的时候调用该借口类的方法
public abstract class Shape {
	public DrawAPI drawAPI;
	public Shape(DrawAPI drawAPI){
		this.drawAPI = drawAPI;
	}
	public abstract void draw();
}
