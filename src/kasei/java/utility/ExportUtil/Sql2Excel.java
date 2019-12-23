package kasei;


import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.CharBuffer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


public class Sql2Excel  {


    private Workbook wb;
    private Sheet sheet;
    private String sql;
    private String[] headers;
    private List<String> excludeAlias;
    private Connection connection;

    /**
     *
     * @param wb 需要存放数据的 Workbook 对象
     * @param sheet 需要存放数据的 Sheet 对象
     * @param sql 查询的 sql
     * @param headers 导出列名，null, "", "   " 会被忽略
     * @param excludeAlias 需要排除的 sql 列名，小写
     * @param connection
     */
    public Sql2Excel(Workbook wb, Sheet sheet, String sql, String[] headers, List<String> excludeAlias, Connection connection) {
        this.wb = wb;
        this.sheet = sheet;
        this.sql = sql;
        this.headers = headers;

        this.excludeAlias = Optional.ofNullable(excludeAlias).orElse(new ArrayList<>()).stream().map(String::toLowerCase).collect(Collectors.toList());
        this.connection = connection;
    }


    public void export2ExcelFromSql() throws Exception {

        String sql = Optional.ofNullable(this.sql).filter(x -> !x.trim().equals("")).get();
        Connection conn = Optional.ofNullable(this.connection).orElseThrow(() -> new RuntimeException("数据库连接不存在"));  // 设置默认链接

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);
            calculateNeededCol(resultSet);
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Export ResultSet to Excel Failure", e);
        } finally {
            closeStrict(this.connection, statement, resultSet);
        }
    }




    private void calculateNeededCol( ResultSet resultSet) throws SQLException, IOException {
        Objects.requireNonNull(resultSet);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<Integer> neededIndex = new ArrayList<>();
        List<String> excludeAlias = Optional.ofNullable(this.excludeAlias).orElse(new ArrayList<>());
        for(int i=0; i<columnCount; i++) {
            String columnLabel = metaData.getColumnLabel(i + 1).toLowerCase();
            if (!excludeAlias.contains(columnLabel)) {
                neededIndex.add(i + 1);
            }
        }
        setValueIntoSheet(resultSet, neededIndex);
    }


    /**
     *
     * @param resultSet
     * @param neededIndex 需要的列的索引，从 1 开始
     * @throws SQLException
     * @throws IOException
     */
    private void setValueIntoSheet(ResultSet resultSet, List<Integer> neededIndex) throws SQLException, IOException {

        if (wb == null || sheet == null || headers == null || resultSet == null || neededIndex == null) {
            return ;
        }


        ResultSetMetaData metaData = resultSet.getMetaData();

        /* 头单元格样式 */
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(font);
        /* 头单元格设值 */
        Row row0 = sheet.createRow(0);
        for (int i = 0; i < neededIndex.size(); i++) {
            Cell cell = row0.createCell(i, Cell.CELL_TYPE_STRING);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(metaData.getColumnLabel(neededIndex.get(i)));
        }
        if(headers != null && headers.length != 0){
            for (int cellIndex = 0, headerIndex =0; headerIndex < headers.length; headerIndex++) {

                if(headers[headerIndex] != null && !"".equals(headers[headerIndex].trim())){
                    Cell cell = row0.createCell(cellIndex, Cell.CELL_TYPE_STRING);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(headers[headerIndex]);
                    cellIndex++;

                }
            }
        }


        // todo 内容单元格样式
        CellStyle cellStyleContent = wb.createCellStyle();
        cellStyleContent.setWrapText(true);

        int j = 1; // excel data row start index
        while (resultSet.next()) {
            Row rowj = sheet.createRow(j);
            for (int i = 0; i < neededIndex.size(); i++) {
                Cell cell = rowj.createCell(i, Cell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyleContent);
                cell.setCellValue(getResultSetValue(resultSet, neededIndex.get(i)));
            }
            j++;
        }
        // todo 自动调整列宽
        for (int i = 0; i < neededIndex.size(); i++) {
            sheet.autoSizeColumn(i);
        }
    }



    /**
     * todo 从 ResultSet 指定列索引中获取值
     * @param resultSet
     * @param colIndex 列索引，从 1 开始
     * @return 从指定列中获取的值
     * @throws SQLException
     * @throws IOException
     * */
    private String getResultSetValue(ResultSet resultSet, int colIndex) throws SQLException, IOException {
        int columnType = resultSet.getMetaData().getColumnType(colIndex);
        switch (columnType) {
            case Types.DECIMAL:
            case Types.NUMERIC: {
                BigDecimal bigDecimal = resultSet.getBigDecimal(colIndex);
                if (bigDecimal != null) {
                    return bigDecimal.toPlainString();
                }
                return "";
            }
            case Types.CHAR:
            case Types.VARCHAR: {
                String str = resultSet.getString(colIndex);
                if(str != null){
                    return str;
                }
                return "";
            }
            case Types.TIMESTAMP:{
                Timestamp timestamp = resultSet.getTimestamp(colIndex);
                if (timestamp != null) {
                    return timestamp.toString();
                }
                return "";
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
                    return sb.toString();
                }
                return "";
            }
            default:{
                return "";
            }
        }
    }


    /** todo 关闭 jdbc 连接，最严格正确的方式
     * */
    public static void closeStrict(Connection conn, Statement stmt, ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
            } catch(Exception e){
                e.printStackTrace();
            } finally{
                if(conn != null){
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
