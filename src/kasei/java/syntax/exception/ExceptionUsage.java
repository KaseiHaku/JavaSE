import org.apache.commons.lang3.exception.ExceptionUtils;

public class ExceptionUsage {
    
    /** todo JDK 原生 */
    public static String getStackTrace2String(Throwable throwable){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
    
    /** todo org.apache.commons.lang3 */
    public static String getStackTrace2StringApache(Throwable throwable){
        return ExceptionUtils.getStackTrace(throwable);
    }
    

}




