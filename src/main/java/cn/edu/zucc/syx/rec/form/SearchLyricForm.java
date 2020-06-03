package cn.edu.zucc.syx.rec.form;

import javax.validation.constraints.NotNull;

public class SearchLyricForm {
    @NotNull
    private String host;

    @NotNull
    private String lyric;

    @NotNull
    private Integer pageNum;

    @NotNull
    private Integer pageSize;

    public SearchLyricForm() {
    }

    public SearchLyricForm(@NotNull String host, @NotNull String lyric, @NotNull Integer pageNum, @NotNull Integer pageSize) {
        this.host = host;
        this.lyric = lyric;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
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
