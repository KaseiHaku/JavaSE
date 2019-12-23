package kasei.javase.designpattern.behavior.filter;

public class Person {
	public String name;
	public int age;
	public int sex;
	
	public Person(String name, int age, int sex){
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	
	@Override
	public String toString(){
		return "person:{"+name+","+age+","+sex+"}";
	}
}
