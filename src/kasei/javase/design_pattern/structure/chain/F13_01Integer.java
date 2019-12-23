package kasei.javase.designpattern.structure.chain;

public class F13_01Integer {
	F13_02Boolean next = null;
	
	public void print(Object obj){
		if(obj instanceof Integer){
			System.out.println("这是一个整型");		
		}else{
			next = new F13_02Boolean();
			next.print(obj);
		}		
	}
}
