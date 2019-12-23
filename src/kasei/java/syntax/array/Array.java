package kasei.javase.se.syntax.array;

class Array {
    public static void main(String[] args) {
        //数组的声明
        int[] a;
        int[][] aa;

        //创建数组 
        a = new int[2];
        aa = new int[2][];
        aa[0] = new int[1];
        aa[1] = new int[2];

        //数组的3种初始化方式
        //1、默认初始化：数组元素的默认值就是各个类型的默认值，引用类型默认值 = null

        //2、动态初始化：
        a[0] = 1;//数组下标是从0开始的
        a[1] = 2;

        aa[0][0] = 3;
        aa[1][0] = 5;
        aa[1][1] = 9;

        //3、静态初始化
        int[] b = {2,4,6};
        int[][] bb = {{1,2}, {4,5,6}, {8,5}};
        Integer c = new Integer[]{1,2,3};
    }
}
