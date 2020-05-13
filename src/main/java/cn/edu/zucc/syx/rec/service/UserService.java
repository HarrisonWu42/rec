package cn.edu.zucc.syx.rec.service;

import cn.edu.zucc.syx.rec.entity.User;
import cn.edu.zucc.syx.rec.form.UserEditForm;
import cn.edu.zucc.syx.rec.form.UserForm;

public interface UserService {

    public User create(UserForm user);

    //删除
    public String delete(User user);

    //局部更新
    public String update(User user);

    User login(String host, String pwd);

    Boolean isUserExist(String host);

    User queryUser(String host);

    User editUser(UserEditForm user);

}
