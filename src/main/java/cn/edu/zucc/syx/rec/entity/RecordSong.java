package cn.edu.zucc.syx.rec.entity;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

public class RecordSong {
    @Field(type = FieldType.Keyword)
    private  String song_id;
    @Field(type = FieldType.Keyword)
    private String song_name;
    @Field(type = FieldType.Keyword)
    private String artist_id;
    @Field(type = FieldType.Keyword)
    private String artist_name;
    @Field(type = FieldType.Keyword)
    private String release;
    @Field(type = FieldType.Date)
    private Date date;
    @Field(type = FieldType.Integer)
    private Integer cnt;

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    @Field(type = FieldType.Keyword)
    private String pic_url;

    public RecordSong(String song_id, String song_name, String artist_id, String artist_name, String release, Date date, String pic_url, int cnt) {
        this.song_id = song_id;
        this.song_name = song_name;
        this.artist_id = artist_id;
        this.artist_name = artist_name;
        this.release = release;
        this.date = date;
        this.pic_url = pic_url;
        this.cnt = cnt;
    }

    public RecordSong() {
    }

    public String getSong_id() {
        return song_id;
    }

    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }


}
