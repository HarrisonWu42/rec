package cn.edu.zucc.syx.rec.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName= "prank", type= "prank")
public class Prank {
    @Id
    @Field(type = FieldType.Keyword)
    private String tag;
    @Field(type = FieldType.Nested)
    private List<KeySong> songs;

}
