package cn.edu.zucc.syx.rec.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSheets {
    @Field(type = FieldType.Keyword)
    private String sheet_id;
    @Field(type = FieldType.Keyword)
    private String sheet_name;
}
