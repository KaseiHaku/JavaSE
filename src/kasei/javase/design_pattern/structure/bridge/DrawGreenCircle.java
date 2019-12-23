package kasei.javase.designpattern.structure.bridge;

public class DrawGreenCircle implements DrawAPI {

	@Override
	public void drawCircle() {
		System.out.println("绿圆");	
	}	
}
