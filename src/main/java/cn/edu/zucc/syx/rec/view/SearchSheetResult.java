package cn.edu.zucc.syx.rec.view;

public class SearchSheetResult {
    private String sheet_id;
    private String sheet_name;
    private String creator_name;
    private String creator_id;
    private Boolean is_collected;
    private String descripiton;

    public SearchSheetResult() {
    }

    public SearchSheetResult(String sheet_id, String sheet_name, String creator_name, String creator_id, Boolean is_collected, String descripiton) {
        this.sheet_id = sheet_id;
        this.sheet_name = sheet_name;
        this.creator_name = creator_name;
        this.creator_id = creator_id;
        this.is_collected = is_collected;
        this.descripiton = descripiton;
    }

    public String getSheet_id() {
        return sheet_id;
    }

    public void setSheet_id(String sheet_id) {
        this.sheet_id = sheet_id;
    }

    public String getSheet_name() {
        return sheet_name;
    }

    public void setSheet_name(String sheet_name) {
        this.sheet_name = sheet_name;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public Boolean getIs_collected() {
        return is_collected;
    }

    public void setIs_collected(Boolean is_collected) {
        this.is_collected = is_collected;
    }

    public String getDescripiton() {
        return descripiton;
    }

    public void setDescripiton(String descripiton) {
        this.descripiton = descripiton;
    }
}
