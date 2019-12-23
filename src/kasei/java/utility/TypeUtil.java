package kasei.utility;

public class TypeUtil {
	
	public static String getType() {
		return "void";
	}
	public static String getType(char var) {
		return "char";
	}
	public static String getType(boolean var) {
		return "boolean";
	}
	public static String getType(byte var) {
		return "byte";
	}
	public static String getType(short var) {
		return "short";
	}
	public static String getType(int var) {
		return "int";
	}
	public static String getType(long var) {
		return "long";
	}
	public static String getType(float var) {
		return "float";
	}
	public static String getType(double var) {
		return "double";
	}
	public static String getType(Object obj) {	
		return obj.getClass().getName();
	}
}
