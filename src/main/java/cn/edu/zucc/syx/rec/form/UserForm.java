package cn.edu.zucc.syx.rec.form;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class UserForm {
    @NotNull
    private String host;

    @NotNull
    private String password;

    private String name;

    private String sex;

    private String email;

    private String phone;
}
