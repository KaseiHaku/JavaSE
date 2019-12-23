package kasei.javase.se.syntax.flowcontrol;

public class FlowControl {


    public static void _condition(){
       String str =  1>2?"r":"b";
    }

    public static void _ifElse(){
        Integer i = 0;
        if(i == 0){
            System.out.println("0");
        } else if(i == 1){
            System.out.println("1");
        } else {
            System.out.println("3");
        }
    }

    public static void _while(){
        int i = 0;
        while(i < 0) {
            System.out.println("while~" + i);
            i++;
        }

        System.out.println();

        i = 0;
        do {
            System.out.println("do-while~"+ i);
            i++;
        }
        while(i< 0);
    }

    public static void _for(){
        int i = 0;

        //逗号操作符:唯一使用的地方就是for语句的初始化和步进控制部分，且这些语句会独立执行
        for(int i1 = 1, j = i + 10; i < 5; i++, j = i * 2) {
            System.out.println("i=" + i + " j=" + j);
        }

        //迭代型for循环：每循环一次就把数组的下一个元素给 ch 变量
        char[] array = {'H', 'e', 'l', 'l', 'o'};
        OUTER:	for(int a=0; a<4; a++) {
            for(char ch: array) {
                System.out.println(ch);
                //break OUTER;//带标号的break：结束标号标记的那层循环
                continue OUTER;//结束标号标记的那层循环的本次循环
            }
        }
        System.out.println("end");
    }



    public static void _switch(){
        switch(1) {//括号内的表达式必须为int char byte short Enum
            case 0:
                System.out.println('0');
                break;

            case 1:
                System.out.println('1');
                //break;//没有break case将持续往下运行

            case 2:
                System.out.println('2');
                break;

            default:
                System.out.println("default");
                break;
        }


        //JDK7的新特性：表达式结果可以为字符串 String！！！
        String str = "地方";
        switch(str) {
            case "草":
                System.out.println("草");
                break;
            case "地方":
                System.out.println("地方");
        }
    }
    
    
    public static String _try_catch_finally(){
        String str = null;
        try{
            str.trim();     // 报空指针异常
            System.out.println("try");
        } catch(NullPointerException e){
            System.out.println("catch");
            return "异常结束";          // 这里 return 也会先执行 finally
        } finally {         // finally 在 return 之前执行
            try{
                str.trim();
                System.out.println("finally");
            } catch(Exception e){
                // 吞掉所有 finally 异常，并输出 log
                System.out.println(e.getMessage());
            } 
        }
        
        System.out.println("normal");
        return "正常结束";
    }

}
