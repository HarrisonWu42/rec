package cn.edu.zucc.syx.rec.entity;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;


public class UserRec {
    @Field(type = FieldType.Nested)
    private List<KeySong> songs;

    @Field(type = FieldType.Nested)
    private List<KeyArtists> artists;

    public List<KeySong> getSongs() {
        return songs;
    }

    public void setSongs(List<KeySong> songs) {
        this.songs = songs;
    }

    public List<KeyArtists> getArtists() {
        return artists;
    }

    public void setArtists(List<KeyArtists> artists) {
        this.artists = artists;
    }

    public UserRec() {
    }

    public UserRec(List<KeySong> songs, List<KeyArtists> artists) {
        this.songs = songs;
        this.artists = artists;
    }
}
