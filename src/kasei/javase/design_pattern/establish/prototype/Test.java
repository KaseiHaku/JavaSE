package kasei.javase.designpattern.establish.prototype;

public class Test {

	public static void main(String[] args) {
		
		Shape shape = new Shape();
		shape.type = "方方的";
		
		Shape shapeClone = null;
		shapeClone = (Shape)shape.clone();
		
		System.out.println(shapeClone.type);
	}
}
