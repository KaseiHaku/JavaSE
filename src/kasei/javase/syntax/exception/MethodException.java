package kasei.javase.se.syntax.exception;

import java.io.IOException;

public class MethodException {
	//若方法体中含有throw语句，抛出的是Checked型异常，且没有在方法体中编写代码处理这些异常，
	//则该方法的声明部分必须加上throws子句，告知JRE该方法会抛出某些异常
	//重写抛出异常的方法只能抛出被重写方法抛出异常的  子类异常或者其本身。
	double dev(double i, double j) throws MyException, IOException {
		if(j == 0)
		{
			throw new MyException("异常：除数为 0 ！");//用编程主动抛出异常对象
		}
		
		return (i / j); 
	}
}
