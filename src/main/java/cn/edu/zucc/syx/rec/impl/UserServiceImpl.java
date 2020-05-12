package cn.edu.zucc.syx.rec.impl;

import cn.edu.zucc.syx.rec.respository.UserRepository;
import cn.edu.zucc.syx.rec.entity.User;
import cn.edu.zucc.syx.rec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 文章serviceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String add(User user){
        try {
            userRepository.save(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "defeated";
        }
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
    public User queryUser(Long id) {
        return null;
    }

//        @Override
//        public User queryUser(Long id) {
//            return null;
//        }
    @Override
    public User queryArticleByNameAndPasswd(String name, String passwd){
        return userRepository.queryByNameAndPassword(name,passwd);
    }
}

