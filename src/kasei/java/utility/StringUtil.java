package kasei.utility;

public class StringUtil {

    /** todo 将字符串中的 html 特殊字符替换为字符实体 */
    public static String convert2HtmlFormat(String str){
        if(str==null || "".equals(str)){
            return ""; // 如果为 null 则前端 html 显示空串
        }
        String result = str.replace("&", "&#38;")
                .replace("<", "&#60;")
                .replace(">", "&#62;")
                .replace("\"", "&#34;") // 双引号
                .replace("'", "&#39;") // 单引号
                .replace("\\", "&#92;") // 反斜杠
                .replace("/", "&#47;") // 斜杠
                .replace("\t","&#09;") // 水平制表符
                .replace("\r", "&#13;") // 回车
                .replace("\n", "&#10;") // 换行
                .replace(" ", "&#160;") // 空格
                //.replace("\v", "&#11;") // 垂直制表符
                .replace("\f", "&#12;") // 换页符
                //.replace("\0", "&#00;") // 空字符 NUL 不转换，有问题
                ;
        return result;
    }
	
    /** TODO 数字随机串生成工具
     * @param length 数字序列号的长度
     * */
    public static String generateDigitRandomStr(Integer length) {
        String result = "";
        for(int i=0; i<length; i++) {
            result = result + Integer.toString((int)(Math.random()*10));
        }

        return result;
    }
	
    /** todo 生成订单号，每秒100万忽略考虑订单号重复的情况
     * */
    public static String generateOrderNumber(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = format.format(new Date());
        String result = "";
        for(int i=0; i<6; i++) {
            result = result + Integer.toString((int)(Math.random()*10));    
        }
        return dateStr+result;
    }

    /** TODO  字符串左填充工具，
     * @param originalStr 被填充的字符串
     * @param fillStr 用作填充的字符串
     * @param totalLength 填充后的总长度
     * @return 填充后的字符串，如果填充不符合规则，那么返回 null
     * */
    public static String leftFill(String originalStr, String fillStr, Integer totalLength) {

        Integer originalLength = originalStr.length();
        Integer fillLength = fillStr.length();
        if(originalLength > totalLength) {
            return null;
        }
        if(originalLength == totalLength) {
            return originalStr;
        }
        if((originalLength+fillLength) < totalLength) {
            return null;
        }
        Integer differenceValue = totalLength - originalLength; // 差值
        String result = fillStr.substring(fillStr.length()-differenceValue, fillStr.length());
        result = result + originalStr;
        return result;
    }



    /** TODO  字符串右填充工具，
     * @param originalStr 被填充的字符串
     * @param fillStr 用作填充的字符串
     * @param totalLength 填充后的总长度
     * @return 填充后的字符串，如果填充不符合规则，那么返回 null
     * */
     public static String rightFill(String originalStr, String fillStr, Integer totalLength) {
        Integer originalLength = originalStr.length();
        Integer fillLength = fillStr.length();
        
        if(originalLength > totalLength) {
            return null;
        }
        if(originalLength == totalLength) {
            return originalStr;
        }
        if((originalLength+fillLength) < totalLength) {
            return null;
        }
        Integer differenceValue = totalLength - originalLength; // 差值
        String result = fillStr.substring(0, differenceValue);
        result = originalStr + result;
        return result;
    }
	
    /**
     * todo 替换字符串中的控制字符 [\x00-\x1F\x7F]
     * */
    public static String cleanCtrlChar(String original){
        if (original == null) {
            return null;
        }
        return original.replaceAll("\\p{Cntrl}", "");
    }
}
