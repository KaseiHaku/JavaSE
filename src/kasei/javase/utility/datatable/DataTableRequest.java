package com.rongyue.efix.controller.utility.request;

import java.util.List;


// 请根据具体的 Datatable 框架版本作调整
public class DataTableRequest {

    private Integer draw;
    private Integer start;
    private Integer length;
    private String searchValue;
    private Boolean searchRegex ;
    private List<Integer> orderColumnList;
    private List<String> orderDirList;
    private List<String> columnsDataList;
    private List<String> columnsNameList;
    private List<Boolean> columnsSearchableList;
    private List<Boolean> columnsOrderableList;
    private List<String> columnsSearchValueList;
    private List<Boolean> columnsSearchRegexList;

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Boolean getSearchRegex() {
        return searchRegex;
    }

    public void setSearchRegex(Boolean searchRegex) {
        this.searchRegex = searchRegex;
    }

    public List<Integer> getOrderColumnList() {
        return orderColumnList;
    }

    public void setOrderColumnList(List<Integer> orderColumnList) {
        this.orderColumnList = orderColumnList;
    }

    public List<String> getOrderDirList() {
        return orderDirList;
    }

    public void setOrderDirList(List<String> orderDirList) {
        this.orderDirList = orderDirList;
    }

    public List<String> getColumnsDataList() {
        return columnsDataList;
    }

    public void setColumnsDataList(List<String> columnsDataList) {
        this.columnsDataList = columnsDataList;
    }

    public List<String> getColumnsNameList() {
        return columnsNameList;
    }

    public void setColumnsNameList(List<String> columnsNameList) {
        this.columnsNameList = columnsNameList;
    }

    public List<Boolean> getColumnsSearchableList() {
        return columnsSearchableList;
    }

    public void setColumnsSearchableList(List<Boolean> columnsSearchableList) {
        this.columnsSearchableList = columnsSearchableList;
    }

    public List<Boolean> getColumnsOrderableList() {
        return columnsOrderableList;
    }

    public void setColumnsOrderableList(List<Boolean> columnsOrderableList) {
        this.columnsOrderableList = columnsOrderableList;
    }

    public List<String> getColumnsSearchValueList() {
        return columnsSearchValueList;
    }

    public void setColumnsSearchValueList(List<String> columnsSearchValueList) {
        this.columnsSearchValueList = columnsSearchValueList;
    }

    public List<Boolean> getColumnsSearchRegexList() {
        return columnsSearchRegexList;
    }

    public void setColumnsSearchRegexList(List<Boolean> columnsSearchRegexList) {
        this.columnsSearchRegexList = columnsSearchRegexList;
    }
}
