package kasei.javase.se.objectoriented.generic;

/**
 * 泛型的嵌套
 */
class K10_04GenericNest {
	public static void main(String[] args) {
	
	}
}

class Banji<T>{
	T stu;
	Banji(T stu){
		this.stu = stu;
	}
}

class Student<T>{
	T name;
	
	Student(T aa){
		this.name = aa;
	}
}

