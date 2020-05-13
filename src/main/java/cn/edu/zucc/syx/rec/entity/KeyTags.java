package cn.edu.zucc.syx.rec.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyTags {
    @Field(type = FieldType.Keyword)
    private  String tag;

    @Field(type = FieldType.Integer)
    private Integer value;
}
