package kasei.javase.designpattern.behavior.command;

public class SellStock implements Command{

	@Override
	public void operate() {
		System.out.println("command： sell stock");	
	}
}
