package kasei.javase.se.frequently.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class F03TestGetClassObj {
	public static void main(String[] args) {
		try {
			Class<?> cls1 = Class.forName("reflect.F01Base");//获取F01Base类的引用,方法一
			Class<?> cls2 = F01Base.class;//获取F01Base类的引用,方法二
			
			F01Base f01Base1 = new F01Base(1);
			Class<?> cls3 = f01Base1.getClass();//获取F01Base类的引用,方法三
			
			Class<?> cls4 = int.class;//基本类型的获取方法
			
			
			//获取类信息
			cls1.getName();//获取类名
			Class<?> cls_super = cls1.getSuperclass();//获取父类的引用
			Class<?>[] cls_interfaces = cls1.getInterfaces();//获取实现接口的引用
			Annotation[] annos = cls1.getAnnotations();//获取类上的注解：继承的+声明的
			Annotation anno = cls1.getAnnotation(F02Ksf.class);//获取类上指定类型的注解：继承的+声明的
			
			//获取构造函数
			Constructor[] constructors = cls1.getConstructors();//获取所有构造函数
			Constructor constructor = cls1.getDeclaredConstructor(int.class);//获取指定带参构造函数
			constructor.getAnnotations();
			constructor.getAnnotation(F02Ksf.class);
					
			//创建对象
			F01Base f01Base2 = (F01Base)cls1.newInstance();//调用无参构造函数创建对象
			F01Base f01Base3 = (F01Base)constructor.newInstance(88);//使用带参构造函数创建对象
			
			
			//获取字段
			Field[] fields = cls1.getDeclaredFields();//获取类所有的字段：仅自己声明的
			Field field = cls1.getDeclaredField("id");//获取指定名称的字段:仅自己声明的
			cls1.getFields();//返回所有public字段：包括继承的
			cls1.getField("id");//返回指定的public字段：包括继承的
			field.setAccessible(true);
			field.getName();//获取字段名
			Modifier.toString(field.getModifiers());//获取该字段的修饰符，并转码
			
			//获取方法
			Method[] methods = cls1.getMethods();//获取所有public方法：包括继承的
			Method method = cls1.getMethod("describe", int.class, String.class);//获取指定名字和参数的public方法：包括继承的，指定参数因为方法能重载
			cls1.getDeclaredMethods();//获取所有的方法：仅自己声明的
			cls1.getDeclaredMethod("describe", int.class, String.class);//获取指定名字和参数的方法：仅自己声明的
			
			//执行反射取得的方法
			method.invoke(f01Base3, 3, "qwer");//调用静态方法把第一个参数置 null 即可
			
			
			//数组的 Class 对象比较：维数不同 和 类型不同 数组的 Class 对象不同
 			int[] ints = new int[8]; 
 			double[] doubles = new double[8];
 			int[][] ints2 = new int[2][3];
 			
 			System.out.println(ints.getClass().hashCode());
 			System.out.println(doubles.getClass().hashCode());
 			System.out.println(ints2.getClass().hashCode());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}		
	}
}
