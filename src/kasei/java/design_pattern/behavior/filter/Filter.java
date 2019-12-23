package kasei.javase.designpattern.behavior.filter;

import java.util.ArrayList;
import java.util.List;

//过滤器模式：根据特定判断条件过滤出容器中的特定对象
public class Filter {
	//过滤出男性
	List<Person> filterMale(List<Person> persons){
		
		List<Person> result = new ArrayList<Person>();
		for(Person p:persons){
			if(p.sex==2){
				result.add(p);
			}
		}
		return result;
	}
}
