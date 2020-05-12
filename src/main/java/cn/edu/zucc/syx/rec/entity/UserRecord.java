package cn.edu.zucc.syx.rec.entity;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class UserRecord {
    @Field(type = FieldType.Keyword)
    private  String song_id;
    @Field(type = FieldType.Keyword)
    private String song_name;

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

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
    @Field(type = FieldType.Keyword)
    private String artist_id;
    @Field(type = FieldType.Keyword)
    private String artist_name;
    @Field(type = FieldType.Keyword)
    private String release;
}
