package kasei.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Java 小数工具 */
public class BigDecimalUtil {

    /** 不做任何处理，直接保留两位小数 */
    public static Double formatFloatNumber(Double d){

        BigDecimal bigDecimal = new BigDecimal(d).setScale(2, RoundingMode.DOWN);
        return bigDecimal.doubleValue();
    }

    /** 保留两位小数，只要第 3 位不是 0 就进位，正数向正无穷进位，负数向负无穷进位，即 1.721=1.73; -1.721 = -1.73 */
    public static Double formatFloatNumber2(Double d){

        BigDecimal bigDecimal = new BigDecimal(d).setScale(2, RoundingMode.UP);
        return bigDecimal.doubleValue();
    }

    /** 保留两位小数，只要第 3 位不是 0 就进位，全部向正无穷进位，即 1.728=1.73; -1.728 = -1.72  */
    public static Double formatFloatNumber3(Double d){

        BigDecimal bigDecimal = new BigDecimal(d).setScale(2, RoundingMode.CEILING);// 天花板
        return bigDecimal.doubleValue();
    }

    /** 保留两位小数，只要第 3 位不是 0 就进位，全部往负无穷方向进位，即 1.728=1.72; -1.721=-1.73 */
    public static Double formatFloatNumber4(Double d){

        BigDecimal bigDecimal = new BigDecimal(d).setScale(2, RoundingMode.FLOOR);// 地板
        return bigDecimal.doubleValue();
    }

    /** 四舍五入 */
    public static Double formatFloatNumber5(Double d){

        BigDecimal bigDecimal = new BigDecimal(d).setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    /** 五舍六入 */
    public static Double formatFloatNumber6(Double d){

        BigDecimal bigDecimal = new BigDecimal(d).setScale(2, RoundingMode.HALF_DOWN);
        return bigDecimal.doubleValue();
    }

    /** 保留两位小数，先四舍五入，如果第 3 位 =5 ，那么看前一位，偶舍奇入，只对整数有效，也就是保留 0 位小数有效 */
    public static Double formatFloatNumber7(Double d){

        BigDecimal bigDecimal = new BigDecimal(d).setScale(1, RoundingMode.HALF_EVEN);
        return bigDecimal.doubleValue();
    }


    /** + */
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    /** - */
    public static BigDecimal sub(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    /** * */
    public static BigDecimal mul(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    /** / */
    public static BigDecimal div(BigDecimal a, BigDecimal b) {
        return a.divide(b, 2, RoundingMode.HALF_UP);
    }


    public static void main(String[] args) {

        System.out.println(formatFloatNumber7(-3.45));
    }

}
