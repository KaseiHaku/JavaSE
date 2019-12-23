package com.rongyue.efix.controller.utility.response;

import java.util.List;

public class DataTableResponse {
    private Integer draw;
    private Integer recordsTotal;
    private Integer recordsFiltered;
    private List<?> data;
    private String error;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordesTotal) {
        this.recordsTotal = recordesTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
/*
上面 data 字段中存储内容的实例
[
    {
        "DT_RowId":"row_1",
        "DT_RowClass":"selected",
        "DT_RowData":{
            "var1":1
        },
        "DT_RowAttr":{
            "attribute1":"attributeValue"
        }
    }
]
*/
