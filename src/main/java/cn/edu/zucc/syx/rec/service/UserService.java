package cn.edu.zucc.syx.rec.service;

import cn.edu.zucc.syx.rec.entity.RecordSong;
import cn.edu.zucc.syx.rec.entity.Song;
import cn.edu.zucc.syx.rec.entity.User;
import cn.edu.zucc.syx.rec.form.UserEditForm;
import cn.edu.zucc.syx.rec.form.UserForm;

import java.util.List;

public interface UserService {

    User create(UserForm user);

    //删除
    String delete(User user);

    //局部更新
    String update(User user);

    User login(String host, String pwd);

    Boolean isUserExist(String host);

    User queryUser(String host);

    User editUser(UserEditForm user);
    RecordSong addRecordSong(String host, String song_id);
    List<RecordSong> listRecordSongs(String host);

}
