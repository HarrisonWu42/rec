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


}
