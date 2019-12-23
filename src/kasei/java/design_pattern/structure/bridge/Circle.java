package kasei.javase.designpattern.structure.bridge;

public class Circle extends Shape {

	public Circle(DrawAPI drawAPI) {
		super(drawAPI);	
	}

	@Override
	public void draw() {
		this.drawAPI.drawCircle();	
	}
}
