package kasei.javase.designpattern.behavior.command;


//命令模式：三种类：命令类、命令调用类、
public class Test {

	public static void main(String[] args) {
		Executer broker = new Executer();
		Command command1 = new BuyStock();//创建命令对象
		Command command2 = new SellStock();
		
		broker.invoke(command1);//通过命令调用类，调用命令
		broker.invoke(command2);
	}
}
