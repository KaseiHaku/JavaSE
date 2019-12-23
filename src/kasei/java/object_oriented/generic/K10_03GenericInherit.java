package kasei.javase.se.objectoriented.generic;

/**
 * 泛型类的继承
 * 1、子类重写父类的方法的类型，随父类而定。
 * 2、子类中使用父类的属性，随父类而定。
 */
abstract class K10_03GenericInherit<T1,T2>{
	T1 age;
	public abstract void test(T2 name);
}

//保留：全部保留
class B1<T1,T2,K,V> extends K10_03GenericInherit<T1,T2> {
	@Override
	public void test(T2 name) {
		System.out.println(this.age);//age 为 T1 类型
	}
}

//保留：部分保留
class B2<T2,K,V> extends K10_03GenericInherit<Integer, T2> {
	@Override
	public void test(T2 name) {
		System.out.println(this.age);//age 为 Integer 类型
	}
}

//不保留：具体类型
class B3<K,V> extends K10_03GenericInherit<Integer, String> {
	@Override
	public void test(String name) {
		System.out.println(this.age);//age 为 Integer 类型
	}
}

//不保留：没有类型（擦除 至Object）
class B4<K,V> extends K10_03GenericInherit<Object, Object> {
	@Override
	public void test(Object name) {
		System.out.println(this.age);//age 为 Object 类型
	}
}