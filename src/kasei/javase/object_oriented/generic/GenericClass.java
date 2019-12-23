package kasei.javase.se.objectoriented.generic;

/** 泛型使用的注意点
 * 1、泛型在使用（继承、实现、创建实例）的时候必须指定明确的某个类型，不能使用有界泛型，有界泛型只能用于声明
 * 2、泛型在使用（继承、实现、创建实例）的时候若没有指定明确的某个类型，则泛型会被擦除到 Object，即相当于指定Object为明确的类型
 * 3、泛型没有数组
 * 4、泛型没有多态
 * 5、List<?>  相当于   List<? extends Object>
 * 6、List<? extends Toy> : 表示 list 中只能存放 Toy及Toy的子类
 * 7、List<? super FancyToy> ： 表示 list 中只能存放 FancyToy 以及他的父类，父类的父类，......
 * */
//自定义泛型类：在类名后面写<>，尖括号里面写单个字母，尽可能见名知意
public class GenericClass<T,E,K,V>{
	public T type;
	public E element;
	public K key;
	public V value;
	//public static T type2;//泛型不能使用在静态属性上
	
	public T getType() {
		return type;
	}
	public void setType(T type) {
		this.type = type;
	}
}
