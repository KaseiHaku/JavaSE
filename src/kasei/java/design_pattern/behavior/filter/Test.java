package kasei.javase.designpattern.behavior.filter;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("张三", 12, 1));
		persons.add(new Person("Haku", 22, 2));
		persons.add(new Person("李四", 16, 1));
		persons.add(new Person("Miku", 16, 2));
		
		Filter filter = new Filter();
		
		List<Person> females = filter.filterMale(persons);
		System.out.println(females.get(0).toString());
		System.out.println(females.toString());
	}
}
