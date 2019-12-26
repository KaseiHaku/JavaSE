/** Java 异常处理中的坑 
 * */
public class ExceptionTrap{
    
    /** Java 异常处理中，return 相关的坑
     * */
    public static Integer returnTrapOfException(){
        System.out.println("start");
        try {
            System.out.println("try begin");
            
            
            /** TODO try 中发生异常：
             * 如果此处发生异常，那么这条语句之后的所有语句都不会被执行，
             * 而是跳转到对应的 catch 块中执行，如果该异常没有被捕获，那么会终止当前 Thread
             * finally 块中肯定会被执行
             * */
            int i = 1/0; 
            
            
            System.out.println("try end");
            return 1; 
        } catch (Exception e) {
            System.out.println("catch begin");
            
            /** TODO catch 中发生异常：
             * 如果此处发生异常，那么这条语句之后的所有语句都不会被执行，
             * 如果该异常没有被捕获，那么会终止当前 Thread 
             * finally 块中还会被执行
             * */
            int i = 2/0; // catch 中发生异常：
            
            
            System.out.println("catch end");
            return 2; // 当当前 catch 捕获到 try 块中抛出的异常，那么该 return 语句会执行
        } finally {
            
            System.out.println("finally begin");
            
            /** TODO catch 中发生异常：
             * 如果此处发生异常，那么这条语句之后的所有语句都不会被执行，
             * 如果该异常没有被捕获，那么会终止当前 Thread ，且 finally 中的异常会覆盖 try 块和 catch 块中的异常
             * */
            String str = null;
            str.equals(""); // finally 中发生异常
            
            
            System.out.println("finally end");
            // 任何情况下，该 return 语句都执行，且在 try return 和 catch return 之后执行，所以会覆盖前两个 return 值
            // 但是是在 System.out.println("end"); 代码之前执行的，所以这里 return 将不会执行之后的代码
            return 3; 
        }
        System.out.println("end");
        return 8;
    }
}
