package kasei.javase.designpattern.structure.bridge;

public class Test {

	public static void main(String[] args) {
		DrawAPI drawAPI = new DrawGreenCircle();
		Shape shape = new Circle(drawAPI);
		
		shape.draw();
	}
}
