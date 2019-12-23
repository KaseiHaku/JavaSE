package kasei.javase.designpattern.behavior.command;


public class Executer {
	
	public void invoke(Command command){
		command.operate();
	}
}
