package cn.edu.zucc.syx.rec.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName= "user", type= "user")
public class User {

    @Id
    @Field(type = FieldType.Keyword)
    private String host;

    @Field(type = FieldType.Keyword)
    private String password;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Keyword)
    private String sex;

    @Field(type = FieldType.Integer)
    private Integer age;

    @Field(type = FieldType.Keyword)
    private String email;

    @Field(type = FieldType.Keyword)
    private String phone;

    @Field(type = FieldType.Nested)
    private UserCollection collection;

    @Field(type = FieldType.Nested)
    private UserRec rec;

    @Field(type = FieldType.Nested)
    private UserRecord record;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserCollection getCollection() {
        return collection;
    }

    public void setCollection(UserCollection collection) {
        this.collection = collection;
    }

    public UserRec getRec() {
        return rec;
    }

    public void setRec(UserRec rec) {
        this.rec = rec;
    }

    public UserRecord getRecord() {
        return record;
    }

    public void setRecord(UserRecord record) {
        this.record = record;
    }

    public User() {
    }

    public User(String host, String password, String name, String sex, Integer age, String email, String phone, UserCollection collection, UserRec rec, UserRecord record) {
        this.host = host;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.collection = collection;
        this.rec = rec;
        this.record = record;
    }
}
