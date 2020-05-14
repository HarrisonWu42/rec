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
@Document(indexName= "song", type= "song")
public class Song {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Float)
    private Float song_hotttnesss;

    @Field(type = FieldType.Keyword)
    private String release;

    @Field(type = FieldType.Integer)
    private Integer year;

    @Field(type = FieldType.Keyword)
    private  String pic_url;

    @Field(type = FieldType.Keyword)
    private String artist_id;

    @Field(type = FieldType.Keyword)
    private String artist_name;

    @Field(type = FieldType.Float)
    private  Float  artist_hotttnesss;

    @Field(type = FieldType.Float)
    private Float artist_familiarity;

    @Field(type = FieldType.Nested)
    private List<KeyTags> tags;

    @Field(type = FieldType.Nested)
    private List<Similar> similar;

    @Field(type = FieldType.Integer)
    private Integer key;

    @Field(type = FieldType.Float)
    private Float loudness;

    @Field(type = FieldType.Float)
    private Float tempo;

    @Field(type = FieldType.Integer)
    private Integer time_signature;

    @Field(type = FieldType.Float)
    private Float duration;

    @Field(type = FieldType.Float)
    private Float end_of_fade_in;

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

    public Float getSong_hotttnesss() {
        return song_hotttnesss;
    }

    public void setSong_hotttnesss(Float song_hotttnesss) {
        this.song_hotttnesss = song_hotttnesss;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
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

    public Float getArtist_hotttnesss() {
        return artist_hotttnesss;
    }

    public void setArtist_hotttnesss(Float artist_hotttnesss) {
        this.artist_hotttnesss = artist_hotttnesss;
    }

    public Float getArtist_familiarity() {
        return artist_familiarity;
    }

    public void setArtist_familiarity(Float artist_familiarity) {
        this.artist_familiarity = artist_familiarity;
    }

    public List<KeyTags> getTags() {
        return tags;
    }

    public void setTags(List<KeyTags> tags) {
        this.tags = tags;
    }

    public List<Similar> getSimilar() {
        return similar;
    }

    public void setSimilar(List<Similar> similar) {
        this.similar = similar;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Float getLoudness() {
        return loudness;
    }

    public void setLoudness(Float loudness) {
        this.loudness = loudness;
    }

    public Float getTempo() {
        return tempo;
    }

    public void setTempo(Float tempo) {
        this.tempo = tempo;
    }

    public Integer getTime_signature() {
        return time_signature;
    }

    public void setTime_signature(Integer time_signature) {
        this.time_signature = time_signature;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Float getEnd_of_fade_in() {
        return end_of_fade_in;
    }

    public void setEnd_of_fade_in(Float end_of_fade_in) {
        this.end_of_fade_in = end_of_fade_in;
    }
}
