package cn.edu.zucc.syx.rec.service;

import cn.edu.zucc.syx.rec.entity.User;

public interface UserService {
    public String add(User user);

    //删除
    public String delete(User user);

    //局部更新
    public String update(User user);

    //查询
    public User queryUser(Long id);

    public User queryArticleByNameAndPasswd(String name, String passwd);

}
