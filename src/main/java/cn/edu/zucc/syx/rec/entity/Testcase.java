package cn.edu.zucc.syx.rec.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.data.repository.query.parser.Part;

import java.util.List;


@Document(indexName= "test", type= "test")
@Setting(settingPath = "elasticsearch/settings.json")
public class Testcase {
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
    private List<Similar> itemcf_w;

    @Field(type = FieldType.Nested)
    private List<Similar> similar;

    @Field(type = FieldType.Keyword)
    private List<String> similar_dl;

    @Field(type = FieldType.Text)
    private String lyric;

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

    public Testcase() {
    }

    public Testcase(String id, String name, Float song_hotttnesss, String release, Integer year, String pic_url, String artist_id, String artist_name, Float artist_hotttnesss, Float artist_familiarity, List<KeyTags> tags, List<Similar> itemcf_w, List<Similar> similar, List<String> similar_dl, String lyric, Integer key, Float loudness, Float tempo, Integer time_signature, Float duration, Float end_of_fade_in) {
        this.id = id;
        this.name = name;
        this.song_hotttnesss = song_hotttnesss;
        this.release = release;
        this.year = year;
        this.pic_url = pic_url;
        this.artist_id = artist_id;
        this.artist_name = artist_name;
        this.artist_hotttnesss = artist_hotttnesss;
        this.artist_familiarity = artist_familiarity;
        this.tags = tags;
        this.itemcf_w = itemcf_w;
        this.similar = similar;
        this.similar_dl = similar_dl;
        this.lyric = lyric;
        this.key = key;
        this.loudness = loudness;
        this.tempo = tempo;
        this.time_signature = time_signature;
        this.duration = duration;
        this.end_of_fade_in = end_of_fade_in;
    }

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

    public List<String> getSimilar_dl() {
        return similar_dl;
    }

    public void setSimilar_dl(List<String> similar_dl) {
        this.similar_dl = similar_dl;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
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

    public List<Similar> getItemcf_w() {
        return itemcf_w;
    }

    public void setItemcf_w(List<Similar> itemcf_w) {
        this.itemcf_w = itemcf_w;
    }
}
