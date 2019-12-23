package com.rongyue.efix.controller.utility;



import com.rongyue.efix.controller.utility.request.DataTableRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class DataTableWrapUtil {

    public static DataTableRequest wrap(HttpServletRequest request){
        Integer draw = Integer.parseInt(request.getParameter("draw"));
        Integer start = Integer.parseInt(request.getParameter("start"));
        Integer length = Integer.parseInt(request.getParameter("length"));
        String searchValue = request.getParameter("search[value]");
        Boolean searchRegex = Boolean.valueOf(request.getParameter("search[regex]"));
        List<Integer> orderColumnList = new ArrayList<Integer>();
        List<String> orderDirList = new ArrayList<String>();
        List<String> columnsDataList = new ArrayList<String>();
        List<String> columnsNameList = new ArrayList<String>();
        List<Boolean> columnsSearchableList = new ArrayList<Boolean>();
        List<Boolean> columnsOrderableList = new ArrayList<Boolean>();
        List<String> columnsSearchValueList = new ArrayList<String>();
        List<Boolean> columnsSearchRegexList = new ArrayList<Boolean>();

        Integer columnCount = 0;
        while(true){
            if(request.getParameter("columns["+columnCount+"][data]") == null){
                break;
            }
            columnCount++;
        }

        for (int i = 0; i < columnCount; i++) {
            String orderColumn = request.getParameter("order["+i+"][column]");
            if(orderColumn != null && orderColumn.equals("") ){
                orderColumnList.add(Integer.parseInt(orderColumn));
            }
            String orderDir = request.getParameter("order["+i+"][dir]");
            if(orderDir != null){
                orderDirList.add(orderDir);
            }

            columnsDataList.add(request.getParameter("columns["+i+"][data]"));
            columnsNameList.add(request.getParameter("columns["+i+"][name]"));
            columnsSearchableList.add(Boolean.valueOf(request.getParameter("columns["+i+"][searchable]")));
            columnsOrderableList.add(Boolean.valueOf(request.getParameter("columns["+i+"][orderable]")));
            columnsSearchValueList.add(request.getParameter("columns["+i+"][search][value]"));
            columnsSearchRegexList.add(Boolean.valueOf(request.getParameter("columns["+i+"][search][regex]")));
        }

        DataTableRequest dataTableRequestUtil = new DataTableRequest();
        dataTableRequestUtil.setDraw(draw);
        dataTableRequestUtil.setStart(start);
        dataTableRequestUtil.setLength(length);
        dataTableRequestUtil.setSearchValue(searchValue);
        dataTableRequestUtil.setSearchRegex(searchRegex);
        dataTableRequestUtil.setOrderColumnList(orderColumnList);
        dataTableRequestUtil.setOrderDirList(orderDirList);
        dataTableRequestUtil.setColumnsDataList(columnsDataList);
        dataTableRequestUtil.setColumnsNameList(columnsNameList);
        dataTableRequestUtil.setColumnsSearchableList(columnsSearchableList);
        dataTableRequestUtil.setColumnsOrderableList(columnsOrderableList);
        dataTableRequestUtil.setColumnsSearchValueList(columnsSearchValueList);
        dataTableRequestUtil.setColumnsSearchRegexList(columnsSearchRegexList);

        return dataTableRequestUtil;
    }
}
