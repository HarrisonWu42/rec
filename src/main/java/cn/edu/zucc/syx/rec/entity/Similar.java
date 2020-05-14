package cn.edu.zucc.syx.rec.entity;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


public class Similar {
    @Field(type = FieldType.Keyword)
    private String song_id;

    @Field(type = FieldType.Float)
    private Float value;

    public String getSong_id() {
        return song_id;
    }

    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Similar() {
    }

    public Similar(String song_id, Float value) {
        this.song_id = song_id;
        this.value = value;
    }
}
