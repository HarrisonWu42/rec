package cn.edu.zucc.syx.rec.form;

import javax.validation.constraints.NotNull;

public class UserEditForm {
    @NotNull
    private String host;

    @NotNull
    private String name;

    @NotNull
    private String sex;

    @NotNull
    private Integer age;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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
}
