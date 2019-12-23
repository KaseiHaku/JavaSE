public class SqlUtil{
  
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
  
    /** todo 输入 sql 直接将结果导出到 excel 中
     * @param sql 不能为 null 
     * @param headers excel 标题
     * @param connection jdbc 数据库连接
     * @return Workbook 实例
     * @trap 支持导出的数据库列类型：number, date, datetime, clob, varchar; 其他类型无法导出
     * */
    public static Workbook export2ExcelFromSql(@NotNull String sql, String[] headers, Connection connection) throws SQLException {

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();

        /*头单元格样式*/
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(font);

        //Connection connection = OracleJdbc.getOracleConnection(); // 获取数据库链接
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        Row row0 = sheet.createRow(0);
        if(headers == null || headers.length == 0){
            for (int i = 0; i < columnCount; i++) {
                Cell cell = row0.createCell(i, Cell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(metaData.getColumnLabel(i+1));
            }
        } else {
            for (int i = 0; i < headers.length; i++) {
                Cell cell = row0.createCell(i, Cell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(headers[i]);
            }
        }

        int j = 1; // excel data row start index
        while (resultSet.next()) {
            Row rowj = sheet.createRow(j);
            for (int i = 0; i < columnCount; i++) {
                int colIndex = i+1;
                int columnType = metaData.getColumnType(colIndex);
                switch (columnType) {
                    case Types.DECIMAL:
                    case Types.NUMERIC: {
                        BigDecimal bigDecimal = resultSet.getBigDecimal(colIndex);
                        if (bigDecimal != null) {
                            rowj.createCell(i, Cell.CELL_TYPE_STRING).setCellValue(bigDecimal.toPlainString());
                        }
                        break;
                    }
                    case Types.CHAR:
                    case Types.VARCHAR: {
                        String str = resultSet.getString(colIndex);
                        if(str != null){
                            rowj.createCell(i, Cell.CELL_TYPE_STRING).setCellValue(str);
                        }
                        break;
                    }
                    case Types.TIMESTAMP:{
                        Timestamp timestamp = resultSet.getTimestamp(colIndex);
                        if (timestamp != null) {
                            rowj.createCell(i, Cell.CELL_TYPE_STRING).setCellValue(timestamp.toString());
                        }
                        break;
                    }
                    case Types.CLOB: {
                        Clob clob = resultSet.getClob(colIndex);
                        if (clob != null) {
                            Reader characterStream = clob.getCharacterStream();
                            CharBuffer charBuffer = CharBuffer.allocate(1024);
                            StringBuilder sb = new StringBuilder(1024);
                            while(characterStream.read(charBuffer)!= -1){
                                charBuffer.flip();
                                sb.append(charBuffer.toString());
                                charBuffer.clear();
                            }
                            rowj.createCell(i, Cell.CELL_TYPE_STRING).setCellValue(sb.toString());
                        }
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
            j++;
        }
        return wb;
    }
  
  
}
