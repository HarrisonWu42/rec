package cn.edu.zucc.syx.rec.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
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
}
