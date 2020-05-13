package cn.edu.zucc.syx.rec.impl;

import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.form.UserEditForm;
import cn.edu.zucc.syx.rec.form.UserForm;
import cn.edu.zucc.syx.rec.respository.UserRepository;
import cn.edu.zucc.syx.rec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(UserForm userForm){
        User user = new User();
        user.setHost(userForm.getHost());
        user.setPassword(userForm.getPassword());
        user.setName(userForm.getName());
        user.setAge(0);
        user.setPhone(userForm.getPhone());
        user.setEmail(userForm.getEmail());
        user.setSex(userForm.getSex());
        UserCollection collection = new UserCollection();
        List<KeySong> collectSongs = new ArrayList<>();
        List<KeyArtists> collectArtists = new ArrayList<>();
        List<UserSheets> collectSheets = new ArrayList<>();
        collection.setArtists(collectArtists);
        collection.setSheets(collectSheets);
        collection.setSongs(collectSongs);
        user.setCollection(collection);

        UserRec rec = new UserRec();
        List<KeySong> recSongs = new ArrayList<>();
        List<KeyArtists> recArtists = new ArrayList<>();
        rec.setSongs(recSongs);
        rec.setArtists(recArtists);
        user.setRec(rec);

        UserRecord record = new UserRecord();
        List<KeySong> recordSongs = new ArrayList<>();
        record.setSongs(recordSongs);
        user.setRecord(record);
        userRepository.save(user);
        return user;
    }

    @Override
    public String delete(User user){
        try {
            userRepository.delete(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "defeated";
        }
    }

    @Override
    public String update(User user){
        try {
            userRepository.save(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "defeated";
        }
    }

    @Override
    public User queryUser(String host){
        return userRepository.findUserByHost(host);
    }

    @Override
    public User editUser(UserEditForm form) {
        User user = userRepository.findUserByHost(form.getHost());
        user.setHost(form.getHost());
        user.setName(form.getName());
        user.setAge(form.getAge());
        user.setPhone(form.getPhone());
        user.setEmail(form.getEmail());
        user.setSex(form.getSex());
        userRepository.save(user);
        return user;
    }

    @Override
    public User login(String host, String pwd){
        return userRepository.queryUserByHostAndPassword(host, pwd);
    }

    @Override
    public Boolean isUserExist(String host){
        User user = userRepository.findUserByHost(host);
        if (user != null){
            return true;
        }
        return false;
    }
}

