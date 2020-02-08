import org.apache.commons.lang3.StringUtils;

public class SqlUtil{
  
    /** TODO 根据开发规范，数据库中数据，必须有默认值，并去除前后空格，不能保存除 ''空串以外的空白串
     * 拼接多值的 sql 条件，去除 null，出去首尾空格 
     * */
    public static String multiValueWhereEqualCondition(String colName, Set<String> vals, Boolean isBlurry){

        if (StringUtils.isBlank(colName)) {
            throw new IllegalArgumentException("列名不能为空");
        }
        if (vals == null || vals.size() == 0) {
            return "";
        } else {
            vals = vals.stream().filter(x -> x != null).map(x -> x.trim()).collect(Collectors.toSet());
            if(vals==null || vals.size()==0){
                return "";
            }
        }
        if(isBlurry == null){
            throw new IllegalArgumentException("是否模糊匹配不能为空");
        }

        StringBuilder result = new StringBuilder();
        vals.stream().forEach( val -> {
            if(isBlurry){
                result.append(" or ").append(colName).append(" like '%").append(val).append("%'");
            } else {
                result.append(" or ").append(colName).append("='").append(val).append("' ");
            }
        });
        if(result.length()!=0){
            result.delete(0,3).insert(0, " and (").append(") ");
        }

        return result.toString();
    }
  
  
  
    /** todo 动态 sql 裁剪
     * @param prefix 裁剪后需要添加的前缀，如果裁剪后为空串，则不添加
     * @param prefixOverride 需要删除的前缀，多个 '|' 分隔
     * @param suffix 裁剪后需要添加的后缀， 如果裁剪后为空串，则不添加
     * @param suffixOverride 需要删除的后缀
     * @return 返回裁剪后的字符串
     * */
    public static String dynamicSqlTrim(String prefix, String prefixOverride, String suffix, String suffixOverride, String content){

        if(content == null || "".equals(content.trim())){ return ""; }
        if (prefix == null) { prefix = ""; }
        if (suffix == null) { suffix = ""; }
        if (prefixOverride == null) { prefixOverride = ""; }
        if (suffixOverride == null) { suffixOverride = ""; }
        prefix = prefix.toLowerCase();
        suffix = suffix.toLowerCase();
        prefixOverride = prefixOverride.toLowerCase();
        suffixOverride = suffixOverride.toLowerCase();
        content = content.toLowerCase();

        String[] prefixOverrideAry = prefixOverride.split("\\|");
        String[] suffixOverrideAry = suffixOverride.split("\\|");
        for (String item : prefixOverrideAry) {
            if ("".equals(item)) {
                continue ;
            }
            content = content.trim();
            if(content.startsWith(item)){
                content = content.substring(item.length());
            }
        }
        for (String item : suffixOverrideAry) {
            if ("".equals(item)) {
                continue ;
            }
            content = content.trim();
            if (content.endsWith(item)) {
                content = content.substring(0, content.length() - item.length());
            }
        }

        if(content.trim().equals("")){
            return "";
        }
        
        return prefix + " " + content + " " + suffix;
    }
  
   
  
}
