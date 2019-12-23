package kasei.javase.se.frequently.reflect;

public @interface F02Ksf {
	int value() default 9;
	
	String str() default "The annotation that what's name is Ksf";
	
	enum Color{RED, GREEN, PURPLE}
	Color color() default Color.RED;
}