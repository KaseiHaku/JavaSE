public class StringSubstitute {

    public void messageFormat(){
        
        /**
         * number 类型的 SubformatPattern
         *      0               数字，没有以 0 补位
         *      #               数字，0 不显示
         *      .               小数点
         *      -               减号
         *      ,               分组分隔符，只有小数点 前 的一个分隔符是有效的
         *      E               科学计数法中，分隔 尾数（mantissa） 和 指数（exponent）
         *      ;               用于分隔 PositivePattern 和 负数 NegativePattern，NegativePattern 只用作指定前后缀，其他以 PositivePattern 为准
         *      %               百分号
         *      ‰ (\u2030)      千分号
         *      ¤ (\u00A4)      货币符号，两个表示使用国际货币符号，如果出现在 pattern 中，则 小数点 替换成 货币小数点
         *      '               原样输出，例如: "'#'#" formats 123 to "#123"
         * 舍入模式： 
         *      RoundingMode.HALF_EVEN
         * */
        System.out.println(MessageFormat.format("number: {0, number}", new BigDecimal("1234.5678")));
        System.out.println(MessageFormat.format("number: {0, number, integer}", new BigDecimal("1234.5678")));
        System.out.println(MessageFormat.format("number: {0, number, currency}", new BigDecimal("1234.5678")));
        System.out.println(MessageFormat.format("number: {0, number, percent}", new BigDecimal("1234.5678")));
        System.out.println(MessageFormat.format("number: {0, number, +(0);-(#)}", new BigDecimal("1234.5678")));
        System.out.println(MessageFormat.format("number: {0, number, +(00000);-(#)}", new BigDecimal("1234.5678")));
        System.out.println(MessageFormat.format("number: {0, number, +(000.#);-(#)}", new BigDecimal("1234.5678")));
        System.out.println(MessageFormat.format("number: {0, number, +(¤000.#);-(#)}", new BigDecimal("1234.5678")));
        System.out.println(MessageFormat.format("number: {0, number, +(¤¤000.#);-(#)}", new BigDecimal("1234.5678")));
        System.out.println(MessageFormat.format("number: {0, number, +('#'000.#);-(#)}", new BigDecimal("1234.5678")));
        System.out.println(MessageFormat.format("number: {0, number, +(000.00 E 00);-(#)}", new BigDecimal("1234.5678")));


        System.out.println(MessageFormat.format("date: {0, date}", new Date()));
        
        System.out.println(MessageFormat.format("time: {0, time}", new Date()));
        
        String choicePattern = "choice: {0, choice, -1#negative| 0#is zero or fraction| 1#is one| 1.0<is 1+| 2#is two| 2<is more than 2}";
        System.out.println(MessageFormat.format( choicePattern, new BigDecimal("-2")));
        System.out.println(MessageFormat.format( choicePattern, new BigDecimal("0")));
        System.out.println(MessageFormat.format( choicePattern, new BigDecimal("1")));
        System.out.println(MessageFormat.format( choicePattern, new BigDecimal("1.1")));
        System.out.println(MessageFormat.format( choicePattern, new BigDecimal("2.1")));


        double[] filelimits = {0,1,2};
        String[] filepart = {"are no files","is one file","are {2} files"};
        ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
        Format[] testFormats = {fileform, null, NumberFormat.getInstance()};
        MessageFormat pattform = new MessageFormat("There {0} on {1}");
        pattform.setFormats(testFormats);
        Object[] testArgs = {null, "ADisk", null};
        for (int i = 0; i < 4; ++i) {
            testArgs[0] = new Integer(i);
            testArgs[2] = testArgs[0];
            System.out.println(pattform.format(testArgs));
        }

        
    }
    
    
    
    public static void main(){
        
    
    }

}
