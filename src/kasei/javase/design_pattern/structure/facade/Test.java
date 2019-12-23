package kasei.javase.designpattern.structure.facade;

//门面模式：隐藏系统的复杂性，并向客户端提供了一个客户端可以访问系统的接口。
public class Test {

	public static void main(String[] args) {
		Facade facade = new Facade();
		facade.execute();
	}
}
