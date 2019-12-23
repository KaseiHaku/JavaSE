/** Java 异常处理中的坑 
 * */
public class ExceptionTrap{
    
    /** Java 异常处理中，return 相关的坑
     * */
    public static Integer returnTrapOfException(){
        System.out.println("start");
        try {
            System.out.println("try");
            throw new Exception("发生异常");
            // return 1; // 此处的 return，如果 try 块中抛异常，那么该 return 语句不会执行
        } catch (Exception e) {
            System.out.println("catch");
            //return 2; // 当当前 catch 捕获到 try 块中抛出的异常，那么该 return 语句会执行
        } finally {
            System.out.println("finally");
            // 任何情况下，该 return 语句都执行，且在 try return 和 catch return 之后执行，所以会覆盖前两个 return 值
            // 但是是在 System.out.println("end"); 代码之前执行的，所以这里 return 将不会执行之后的代码
            //return 3; 
        }
        System.out.println("end");
        return 8;
    }
}
