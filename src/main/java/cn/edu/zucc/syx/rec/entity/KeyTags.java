package cn.edu.zucc.syx.rec.entity;


import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class KeyTags {
    @Field(type = FieldType.Keyword)
    private  String tag;

    @Field(type = FieldType.Integer)
    private Integer value;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public KeyTags() {
    }

    public KeyTags(String tag, Integer value) {
        this.tag = tag;
        this.value = value;
    }
}
