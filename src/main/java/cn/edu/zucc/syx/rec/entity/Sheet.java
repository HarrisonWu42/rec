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
@Document(indexName= "sheet", type= "sheet")
public class Sheet {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Keyword)
    private String creator_id;
    @Field(type = FieldType.Keyword)
    private String creator_name;
    @Field(type = FieldType.Boolean)
    private Boolean is_open;
    @Field(type = FieldType.Nested)
    private List<KeySong> songs;

}
