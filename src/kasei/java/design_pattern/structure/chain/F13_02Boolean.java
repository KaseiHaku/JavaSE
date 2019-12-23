package kasei.javase.designpattern.structure.chain;

public class F13_02Boolean {
	F13_03String next = null;
	
	public void print(Object obj){
		if(obj instanceof Boolean){
			System.out.println("这是一个布尔型");	
		}else{
			next = new F13_03String();
			next.print(obj);
		}
	}
}
