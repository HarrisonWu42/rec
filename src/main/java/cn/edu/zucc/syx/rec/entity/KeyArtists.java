package cn.edu.zucc.syx.rec.entity;


import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class KeyArtists {
    @Field(type = FieldType.Keyword)
    private String artist_id;

    @Field(type = FieldType.Keyword)
    private String artist_name;

    public KeyArtists() {
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

    public KeyArtists(String artist_id, String artist_name) {
        this.artist_id = artist_id;
        this.artist_name = artist_name;
    }
}
