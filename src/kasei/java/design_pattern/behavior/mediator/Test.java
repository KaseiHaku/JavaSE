package kasei.javase.designpattern.behavior.mediator;

//中介者模式：
public class Test {

	public static void main(String[] args) {
		User user1 = new User("kasei");
		User user2 = new User("haku");
		user1.sendMessage("this is kasei");
		user2.sendMessage("this is haku");
	}
}
