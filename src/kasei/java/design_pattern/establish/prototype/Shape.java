package kasei.javase.designpattern.establish.prototype;

public class Shape implements Cloneable{

    String type = null;

	@Override
	protected Object clone(){
		
		Object clone = null;
		try {
			clone = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}	
	

}
