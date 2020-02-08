public class Outer{
    public static String outerField = "23";
    public class Inner{
        public static String innerField = outerField; // 所以加载顺序为先外部类，再内部类
    }


}
