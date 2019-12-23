package kasei.javase.designpattern.structure.chain;

public class F13_03String {
	//F13_02Boolean next = null; //责任链最后一环，没有下一环了
	
	public void print(Object obj){
		if(obj instanceof String){
			System.out.println("这是一个字符串");
		}		
	}
}
