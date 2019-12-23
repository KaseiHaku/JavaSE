package kasei.javase.designpattern.behavior.mediator;

public class User {
	public String name = null;
	public User(String name){
		this.name = name;
	}
	public void sendMessage(String message){
		ChatRoom.showMessage(this, message);
	}
}
