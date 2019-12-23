package kasei.utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * @trap 坑：本工具只支持 excel 文本格式的单元格处理
 * */
public class ExcelUtil {

     /**
     * TODO 从 Excel 单元格中获取内容
     * @param cell 指定的 excel cell
     * @return 单元格中的值，字符串格式
     * @throws Exception
     * @trap 只支持一部分格式的单元格，使用时需要调整
     * */
    public static String getExcelCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_STRING: {
                return cell.getStringCellValue().trim();
            }
            case Cell.CELL_TYPE_NUMERIC: {
                double numericCellValue = cell.getNumericCellValue();
                return Double.toString(numericCellValue).trim();
            }
            case Cell.CELL_TYPE_BOOLEAN:{
                boolean booleanCellValue = cell.getBooleanCellValue();
                return Boolean.toString(booleanCellValue).trim();
            }
            case Cell.CELL_TYPE_FORMULA:{
                return cell.getCellFormula().trim();
            }
            case Cell.CELL_TYPE_BLANK:
            case Cell.CELL_TYPE_ERROR:{
                return "";
            }
            default:{
                throw new RuntimeException("未知的 excel 单元格类型");
            }
        }
    }
    
    
    public static void demo() throws IOException {
        Workbook wb = new XSSFWorkbook("C:\\Users\\kys2081\\Desktop\\Dimension.xlsx");
        Workbook wb2 = new XSSFWorkbook();
        Sheet sheet0 = wb.getSheetAt(0);

        // 替换单值标记
        String labelValue = "<labelValue>";
        setLabelSingleValue(sheet0, labelValue, "wqeeeeeeeeeee");

        // 替换列标记
        String labelColumn = "<labelColumn>";
        List<String> oneDimensionListColumn = new ArrayList<>();
        oneDimensionListColumn.add("col1");
        oneDimensionListColumn.add("col2");
        oneDimensionListColumn.add("col3");
        setLabelMultipleValueColumn(sheet0, labelColumn, oneDimensionListColumn);

        // 替换行标记
        String labelRow = "<labelRow>";
        List<String> oneDimensionListRow = new ArrayList<>();
        oneDimensionListRow.add("row1");
        oneDimensionListRow.add("row2");
        oneDimensionListRow.add("row3");
        setLabelMultipleValueRow(sheet0, labelRow, oneDimensionListRow);

        // 替换表标记，没写
        String labelTable = "<labelTable>";
        List<List<String>> twoDimensionList = new ArrayList<>();
        List<String> subOneDimensionList1 = new ArrayList<>();
        List<String> subOneDimensionList2 = new ArrayList<>();
        twoDimensionList.add(subOneDimensionList1);
        twoDimensionList.add(subOneDimensionList2);
        setLabelMultipleValueTable(sheet0, labelTable, twoDimensionList);

        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\kys2081\\Desktop\\Dimension2.xlsx"));
        wb.write(fos);
        fos.flush();
        fos.close();
        //wb.close(); // 不能关闭该 wb 实例，否则会将数据保存到原文件中，导致覆盖了模板
    }



    public static void setLabelSingleValue(Sheet sheet, String label, String value){
        Integer[] labelCoordinate = getLabelCoordinate(sheet, label);
        if (labelCoordinate != null) {
            setByCoordinate(sheet, labelCoordinate, value);
        }
    }
    public static void setLabelMultipleValueColumn(Sheet sheet, String label, List<String> oneDimensionList){
        Integer[] labelCoordinate = getLabelCoordinate(sheet, label);
        if (labelCoordinate != null) {
            List<String> originalColData = getColumnData(sheet, labelCoordinate[1]);
            originalColData.remove(labelCoordinate[0].intValue()); // 覆盖原来的标签
            for (int i = 0; i < oneDimensionList.size(); i++) {
                originalColData.add(labelCoordinate[0] + i, oneDimensionList.get(i));
            }
            setColumnData(sheet, labelCoordinate[1], originalColData);
        }
    }
    public static void setLabelMultipleValueRow(Sheet sheet, String label, List<String> oneDimensionList){
        Integer[] labelCoordinate = getLabelCoordinate(sheet, label);
        if (labelCoordinate != null) {
            List<String> originalRowData = getRowData(sheet, labelCoordinate[0]);
            originalRowData.remove(labelCoordinate[1].intValue()); // 覆盖原来的标签
            for (int i = 0; i < oneDimensionList.size(); i++) {
                originalRowData.add(labelCoordinate[1] + i, oneDimensionList.get(i));
            }
            setRowData(sheet, labelCoordinate[0], originalRowData);
        }
    }
    public static void setLabelMultipleValueTable(Sheet sheet, String label, List<List<String>> twoDimensionsList){

    }




    public static List<String> getColumnData(Sheet sheet, Integer colNum){
        if (sheet != null) {
            List<String> strList = new ArrayList<>();
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 0; i < lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    strList.add(i, null);
                } else {
                    Cell cell = row.getCell(colNum);
                    if (cell == null) {
                        strList.add(i, null);
                    } else {
                        strList.add(i, cell.getStringCellValue());
                    }
                }
            }
            return strList;
        }
        return null;
    }
    public static void setColumnData(Sheet sheet, Integer colNum, List<String> colData){
        Integer[] variedCoordinate = new Integer[2];
        variedCoordinate[1] = colNum;
        for (int i = 0; i < colData.size(); i++) {
            variedCoordinate[0] = i;
            setByCoordinate(sheet, variedCoordinate, colData.get(i));
        }
    }
    public static List<String> getRowData(Sheet sheet, Integer rowNum){
        if (sheet != null) {
            List<String> strList = new ArrayList<>();
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                return null;
            }
            int lastCellNum = row.getLastCellNum();
            for (int i = 0; i < lastCellNum; i++) {
                Cell cell = row.getCell(i);
                if (cell == null) {
                    strList.add(i, null);
                } else {
                    strList.add(i, cell.getStringCellValue());
                }
            }
            return strList;
        }
        return null;
    }
    public static void setRowData(Sheet sheet, Integer rowNum, List<String> rowData){
        Integer[] variedCoordinate = new Integer[2];
        variedCoordinate[0] = rowNum;
        for (int i = 0; i < rowData.size(); i++) {
            variedCoordinate[1] = i;
            setByCoordinate(sheet, variedCoordinate, rowData.get(i));
        }
    }


    /** todo 在指定位置插入行
     * @param sheet 表实例
     * @param rowNum 表示要插入位置的当前行号
     * @param insertNum 表示要插入的行数
     * @param preposition true 表示在当前行之前插入
     * */
    public static void insertRow(Sheet sheet, Integer rowNum, Integer insertNum, Boolean preposition){
        /*if (preposition) {
            sheet.shiftRows(5, rowNum, insertNum);
        }*/

    }


    /** todo 根据标记获取单元格坐标
     * @param sheet 表实例
     * @param label 标记
     * @return 单元格坐标： rowNum == coordinate[0]; colNum == coordinate[1]
     * */
    public static Integer[] getLabelCoordinate(Sheet sheet, String label){
        if (sheet == null || label == null || "".equals(label)) {
            return null;
        }
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                short lastCellNum = row.getLastCellNum();
                for (int j = 0; j <= lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        CellType cellType = cell.getCellType();
                        if(cellType.equals(CellType.STRING)) {
                            String str = cell.getStringCellValue();
                            if (str != null) {
                                if (label.equals(str)) {
                                    return new Integer[]{i, j};
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }



    /** todo 根据指定坐标设置数据
     * @param sheet excel 表实例
     * @param coordinate 坐标 rowNum == coordinate[0]; colNum == coordinate[1]
     * @return 单元格中的值，如果单元格不存在则返回 null
     * @trap 坑：只能读取文本格式单元格中的数据
     * @attention 如果 sheet 实例中不存在指定坐标的 cell，那么会自动创建该 cell
     * */
    public static String getByCoordinate(Sheet sheet, Integer[] coordinate) {
        if (sheet != null) {
            Row row = sheet.getRow(coordinate[0]);
            if (row == null) {
                return null;
            }
            Cell cell = row.getCell(coordinate[1]);
            if (cell == null) {
                return null;
            }
            return cell.getStringCellValue();
        }
        return null;
    }


    /** todo 根据指定坐标设置数据
     * @param sheet excel 表实例
     * @param coordinate 坐标 rowNum == coordinate[0]; colNum == coordinate[1]
     * @param value 需要设置的值
     * @attention 如果 sheet 实例中不存在指定坐标的 cell，那么会自动创建该 cell
     * */
    public static void setByCoordinate(Sheet sheet, Integer[] coordinate, String value) {
        if (sheet != null) {
            Row row = sheet.getRow(coordinate[0]);
            if (row == null) {
                row = sheet.createRow(coordinate[0]);
            }
            Cell cell = row.getCell(coordinate[1]);
            if (cell == null) {
                cell = row.createCell(coordinate[1]);
            }
            cell.setCellValue(value);
        }
    }
    
    
    
    // 新建一个 excel 文件并横向填充数据
    public static Workbook fillDataHorizontal(List<List<String>> lists){
        Workbook workbook = new XSSFWorkbook(); // 在内存中创建一个工作簿
        Sheet sheet = workbook.createSheet("temporary");// 创建一张表
        for (Integer i=0; i<lists.size(); i++) {
            Row row = sheet.createRow(i);// 创建一行，行号从0开始
            for (Integer j=0; j<lists.get(i).size(); j++) {
                row.createCell(j).setCellValue(lists.get(i).get(j));
            }
        }
        return workbook;
    }

    // 新建一个 excel 文件并纵向填充数据
    public static Workbook fillDataVertical(List<List<String>> lists){
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("temporary");

        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {

                Row row = sheet.getRow(j);
                if(row==null){ // 如果第一行不存在
                    row = sheet.createRow(j);
                }
                Cell cell = row.getCell(i);
                if(cell == null){ // 如果第一行第一个单元格不存在
                    cell = row.createCell(i);
                }
                cell.setCellValue(lists.get(i).get(j));
            }
        }
        return workbook;
    }
    
    
     
}
