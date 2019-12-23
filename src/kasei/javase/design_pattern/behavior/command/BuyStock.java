package kasei.javase.designpattern.behavior.command;

public class BuyStock implements Command{

	@Override
	public void operate() {
		System.out.println("command： buy stock");
		
	}

}
