package cn.edu.zucc.syx.rec.entity;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

public class SongSimDl {
    @Field(type = FieldType.Keyword)
    private String song_id;
    @Field(type = FieldType.Keyword)

    private List<String> simDLSongs;
}
