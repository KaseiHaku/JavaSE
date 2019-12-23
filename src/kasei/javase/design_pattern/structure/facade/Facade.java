package kasei.javase.designpattern.structure.facade;

public class Facade {
	A a = null;
	B b = null;
	C c = null;
	
	public 	Facade(){
		this.a = new A();
		this.b = new B();
		this.c = new C();
	}
	
	public void execute(){
		a.print();
		b.draw();
		c.cout();
	}
}
