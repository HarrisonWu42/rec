package cn.edu.zucc.syx.rec.form;

import javax.validation.constraints.NotNull;

public class SearchSongForm {
    @NotNull
    private String host;

    @NotNull
    private String songName;

    @NotNull
    private Integer pageNum;

    @NotNull
    private Integer pageSize;

    public SearchSongForm() {
    }

    public SearchSongForm(@NotNull String host, @NotNull String songName, @NotNull Integer pageNum, @NotNull Integer pageSize) {
        this.host = host;
        this.songName = songName;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
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
