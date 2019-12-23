package kasei.javase.se.syntax.exception;


public class MyException extends Exception {
	/* 所有标准的异常类都有两个构造函数，一个默认构造函数，
	 * 另一个是接收字符串作为参数，把相关信息放入异常对象的构造函数
	 **/
	public MyException() {
		
	}

	public MyException(String message) {
	    super(message);
	}
}
