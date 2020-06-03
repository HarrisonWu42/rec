package cn.edu.zucc.syx.rec.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName= "prank", type= "prank")
public class Prank {
    public Prank() {

    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Prank(String tag, List<KeySong> songs) {
        this.tag = tag;
        this.songs = songs;
    }

    public List<KeySong> getSongs() {
        return songs;
    }

    public void setSongs(List<KeySong> songs) {
        this.songs = songs;
    }

    @Id
    @Field(type = FieldType.Keyword)
    private String tag;
    @Field(type = FieldType.Nested)
    private List<KeySong> songs;

}
