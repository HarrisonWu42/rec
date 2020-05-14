package cn.edu.zucc.syx.rec.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Document(indexName= "artist", type= "artist")
public class Artist {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Keyword)
    private  String name;

    @Field(type = FieldType.Float)
    private  Float artist_familiarity;

    @Field(type = FieldType.Float)
    private  Float artist_hotttnesss;

    @Field(type = FieldType.Nested)
    private List<ArtistsTags> tags;

    @Field(type = FieldType.Nested)
    private List<KeyArtists> similar;

    @Field(type = FieldType.Nested)
    private List<KeySong> songs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getArtist_familiarity() {
        return artist_familiarity;
    }

    public void setArtist_familiarity(Float artist_familiarity) {
        this.artist_familiarity = artist_familiarity;
    }

    public Float getArtist_hotttnesss() {
        return artist_hotttnesss;
    }

    public void setArtist_hotttnesss(Float artist_hotttnesss) {
        this.artist_hotttnesss = artist_hotttnesss;
    }

    public List<ArtistsTags> getTags() {
        return tags;
    }

    public void setTags(List<ArtistsTags> tags) {
        this.tags = tags;
    }

    public List<KeyArtists> getSimilar() {
        return similar;
    }

    public void setSimilar(List<KeyArtists> similar) {
        this.similar = similar;
    }

    public List<KeySong> getSongs() {
        return songs;
    }

    public void setSongs(List<KeySong> songs) {
        this.songs = songs;
    }
}
