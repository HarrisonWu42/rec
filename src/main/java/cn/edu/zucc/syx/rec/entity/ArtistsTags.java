package cn.edu.zucc.syx.rec.entity;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class ArtistsTags {
    @Field(type = FieldType.Keyword)
    private String tag;
    @Field(type = FieldType.Float)
    private Float freq;
    @Field(type = FieldType.Float)
    private Float weight;
}
