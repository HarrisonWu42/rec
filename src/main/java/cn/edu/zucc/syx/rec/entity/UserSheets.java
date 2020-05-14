package cn.edu.zucc.syx.rec.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSheets {
    @Field(type = FieldType.Keyword)
    private String sheet_id;

    @Field(type = FieldType.Keyword)
    private String usersheet_name;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private String creator_id;

    @Field(type = FieldType.Keyword)
    private String creator_name;

    @Field(type = FieldType.Boolean)
    private Boolean is_open;
}
