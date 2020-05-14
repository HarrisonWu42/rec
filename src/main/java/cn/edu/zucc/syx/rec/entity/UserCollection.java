package cn.edu.zucc.syx.rec.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class UserCollection {
    @Field(type = FieldType.Nested)
    private List<KeySong> songs;

    @Field(type = FieldType.Nested)
    private List<KeyArtists> artists;

    @Field(type = FieldType.Nested)
    private List<UserSheets> sheets;

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

    public List<UserSheets> getSheets() {
        return sheets;
    }

    public void setSheets(List<UserSheets> sheets) {
        this.sheets = sheets;
    }
}
