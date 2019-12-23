package kasei.javase.designpattern.structure.decorator;

public class RedShapeDecorator extends ShapeDecorator {

	public RedShapeDecorator(Shape shape) {
		super(shape);
		
	}
	
	@Override
	public void draw(){
		this.shape.draw();
		System.out.println("border is red!");
	}
}
