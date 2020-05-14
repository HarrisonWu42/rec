package cn.edu.zucc.syx.rec.entity;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

public class UserRecord {
    @Field(type = FieldType.Nested)
    private List<KeySong> songs;

    public List<KeySong> getSongs() {
        return songs;
    }

    public void setSongs(List<KeySong> songs) {
        this.songs = songs;
    }

    public UserRecord() {
    }

    public UserRecord(List<KeySong> songs) {
        this.songs = songs;
    }
}
