package kasei.javase.se.syntax.exception;

public class ConstructorException {
	/** 构造方法异常
	 * 1、抛出非受检查异常
	 * 		a.在构造方法中抛出非受检查的异常，程序员无法处理该异常
	 * 		
	 * 2、抛出收检查的异常
	 * 		a、若子类构造方法也抛出了异常，且异常是父类异常的超类，那么久违背了 里氏替换原则：任何子类能出现的地方，父类都能出现且不会出现任何问题
	 * 		b、导致子类不能使用默认的构造方法
     *      c、子类构造函数难以扩展
	 * 结论：非受检查异常不要抛出,抛出了对人对己都是有害的...受检查异常尽量不抛出.总之一句话:在构造函数中尽可能的不出现异常.
	 * */	
	public ConstructorException(int age) throws RuntimeException {
		if(age < 18){
			throw new RuntimeException("年龄必须大于18岁。");		
		}	
		System.out.println("构造函数抛出异常后这段代码不执行了！");
	}
	
	//看限制级的电影
    public void seeMovie(){

	    System.out.println("看限制级电影");
    }
}
