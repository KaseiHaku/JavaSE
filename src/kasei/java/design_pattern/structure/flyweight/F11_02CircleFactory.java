package kasei.javase.designpattern.structure.flyweight;

import java.util.HashMap;

public class F11_02CircleFactory {
	private static final HashMap<String, F11_01Circle> map = new HashMap<>();
	
	public F11_01Circle getCircle(String color){
		F11_01Circle circle = map.get(color);
		if(circle == null){
			circle = new F11_01Circle();
			map.put(color, circle);
			System.out.println("create circle who's color is : " + color);
		}
		return circle;
	}
}
