package kasei.javase.designpattern.behavior.command;


/** 命令模式：
 * Command: 命令，在 HTTP 中就相当于 请求体
 * Receiver：命令接收者，在 HTTP 中就相当于 Server
 * Sender：命令发送者，在 HTTP 中相当于 Browser 或者 PostMan
 * */
public class Test {

	public static void main(String[] args) {
		Executer broker = new Executer();
		Command command1 = new BuyStock();//创建命令对象
		Command command2 = new SellStock();
		
		broker.invoke(command1);//通过命令调用类，调用命令
		broker.invoke(command2);
	}
}
