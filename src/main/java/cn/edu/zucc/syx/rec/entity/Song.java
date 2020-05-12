package cn.edu.zucc.syx.rec.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName= "song", type= "song")
public class Song {
    @Id
    @Field(type = FieldType.Integer)
    private Integer id;
    @Field(type = FieldType.Keyword)
    private String song_id;
    @Field(type = FieldType.Keyword)
    private String song_name;
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

}
