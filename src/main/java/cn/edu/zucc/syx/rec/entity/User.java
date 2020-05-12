package cn.edu.zucc.syx.rec.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by yemengying on 16/1/10.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName= "user", type= "user")
public class User {

    @Id
    @Field(type = FieldType.Integer)
    private Integer id;

//    @Field(type = FieldType.Text)
//    private String account;
    @Field(type = FieldType.Keyword)
    private String host;
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Keyword)
    private String password;
    @Field(type = FieldType.Keyword)
    private String sex;
    @Field(type = FieldType.Integer)
    private Integer age;
    @Field(type = FieldType.Keyword)
    private String email;
    @Field(type = FieldType.Keyword)
    private String phone;
//    @Field(type = FieldType.Nested)
//    private UserSong song;
    @Field(type = FieldType.Nested)
    private UserCollect collect;
    @Field(type = FieldType.Nested)
    private UserRec rec;



}
