package kasei.javase.se.frequently.reflect;



@F02Ksf(11)
public class F01Base {

	@F02Ksf(22)
	private int id = 99;

	F01Base(){}

	@F02Ksf(33)
	F01Base(int id){
		this.id = id;
	}
	
	@F02Ksf(value=44, str="kasei", color= F02Ksf.Color.PURPLE)
	public String describe(int i, String str){
		return null;
	}
}
