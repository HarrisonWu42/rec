package cn.edu.zucc.syx.rec.entity;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.sql.Timestamp;
import java.util.Date;

public class RecordSong {
    public String getSong_id() {
        return song_id;
    }

    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }

    public RecordSong(String song_id, Date date) {
        this.song_id = song_id;
        this.date = date;
    }
    public RecordSong() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Field(type = FieldType.Keyword)
    private  String song_id;
    @Field(type = FieldType.Date)
    private Date date;

}
