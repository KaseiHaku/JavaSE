package kasei.javase.designpattern.behavior.memento;

public class Originator {
	public String state;
	public Memento save(){
		return new Memento(this.state);
	}
}
