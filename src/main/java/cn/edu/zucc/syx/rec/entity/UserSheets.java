package cn.edu.zucc.syx.rec.entity;


import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


public class UserSheets {
    @Field(type = FieldType.Keyword)
    private String sheet_id;

    @Field(type = FieldType.Keyword)
    private String sheet_name;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private String creator_id;

    @Field(type = FieldType.Keyword)
    private String creator_name;

    @Field(type = FieldType.Boolean)
    private Boolean is_open;

    public String getSheet_id() {
        return sheet_id;
    }

    public void setSheet_id(String sheet_id) {
        this.sheet_id = sheet_id;
    }

    public String getSheet_name() {
        return sheet_name;
    }

    public void setSheet_name(String usersheet_name) {
        this.sheet_name = usersheet_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public Boolean getIs_open() {
        return is_open;
    }

    public void setIs_open(Boolean is_open) {
        this.is_open = is_open;
    }

    public UserSheets() {
    }

    public UserSheets(String sheet_id, String sheet_name, String description, String creator_id, String creator_name, Boolean is_open) {
        this.sheet_id = sheet_id;
        this.sheet_name = sheet_name;
        this.description = description;
        this.creator_id = creator_id;
        this.creator_name = creator_name;
        this.is_open = is_open;
    }
}
