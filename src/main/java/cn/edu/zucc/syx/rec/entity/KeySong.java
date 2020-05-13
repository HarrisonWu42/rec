package cn.edu.zucc.syx.rec.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeySong {
    @Field(type = FieldType.Keyword)
    private  String song_id;
    @Field(type = FieldType.Keyword)
    private String song_name;
    @Field(type = FieldType.Keyword)
    private String artist_id;
    @Field(type = FieldType.Keyword)
    private String artist_name;
    @Field(type = FieldType.Keyword)
    private String release;


}
