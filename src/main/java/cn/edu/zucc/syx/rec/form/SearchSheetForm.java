package cn.edu.zucc.syx.rec.form;

import javax.validation.constraints.NotNull;

public class SearchSheetForm {
    @NotNull
    private String host;

    @NotNull
    private String sheetName;

    @NotNull
    private Integer pageNum;

    @NotNull
    private Integer pageSize;

    public SearchSheetForm() {
    }

    public SearchSheetForm(@NotNull String host, @NotNull String sheetName, @NotNull Integer pageNum, @NotNull Integer pageSize) {
        this.host = host;
        this.sheetName = sheetName;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
