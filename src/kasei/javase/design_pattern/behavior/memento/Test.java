package kasei.javase.designpattern.behavior.memento;

//备忘录模式：
public class Test {

	public static void main(String[] args) {
		Originator o = new Originator();
		o.state = "state 1:wo cao ";
		Memento m = o.save();//存入备忘录
		o.state = "dfadgfqe";
		o.state = m.state;//从备忘录中恢复
		System.out.println(o.state);
	}
}
