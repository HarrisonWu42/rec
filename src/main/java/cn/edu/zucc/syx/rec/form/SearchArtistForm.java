package cn.edu.zucc.syx.rec.form;

import javax.validation.constraints.NotNull;

public class SearchArtistForm {
    @NotNull
    private String host;

    @NotNull
    private String artistName;

    @NotNull
    private Integer pageNum;

    @NotNull
    private Integer pageSize;

    public SearchArtistForm() {
    }

    public SearchArtistForm(@NotNull String host, @NotNull String artistName, @NotNull Integer pageNum, @NotNull Integer pageSize) {
        this.host = host;
        this.artistName = artistName;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
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
