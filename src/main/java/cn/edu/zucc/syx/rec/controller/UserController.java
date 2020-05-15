package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.form.UserEditForm;
import cn.edu.zucc.syx.rec.form.UserForm;
import cn.edu.zucc.syx.rec.impl.UserServiceImpl;
import cn.edu.zucc.syx.rec.util.JsonUtil;
import cn.edu.zucc.syx.rec.util.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;

@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    private JsonUtil util = new JsonUtil();

    /**
     *  注册
     */
    @PostMapping("/register")
    public JSONObject register(@RequestBody UserForm userForm) {
        JSONObject ret = new JSONObject();

        // host已经存在
        if (userService.isUserExist(userForm.getHost()) == true) {
            ret.put("code", "error");
            ret.put("msg", "该用户已经存在");
        }

        // 默认name,随机
        if (userForm.getName() == null){
            userForm.setName(Tool.getRandomString(8));
        }

        try {
            User user = userService.create(userForm);
            ret = util.user2JSON(user);
        } catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }

        return ret;
    }

    /**
     * 登陆
     */
    @PostMapping("/login/{host}")
    public JSONObject login(@PathVariable("host") String host, @PathParam("password") String pwd) throws Exception {
        JSONObject ret = new JSONObject();

        if (userService.isUserExist(host) == false){
            ret.put("code", "error");
            ret.put("msg", "账号不存在");
            return ret;
        }

        try {
            User user = userService.login(host, pwd);
            if (user == null) {
                ret.put("code", "error");
                ret.put("msg", "密码错误");
                return ret;
            } else {
                ret = util.user2JSON(user);
            }
        } catch (Exception e) {
            ret.put("code", "error");
            ret.put("msg", "failed");
        }

        return ret;
    }

    /**
     * 获取个人信息
     */
    @GetMapping("/{host}/get_personal_info")
    public JSONObject getPersonalInfo(@PathVariable("host") String host) {
        User user = userService.queryUser(host);
        JSONObject ret = util.userInfo2Json(user);
        return ret;
    }

    /**
     * 修改个人信息（待修改）
     * 目前有个问题，就是个人创建的歌单中有个创建者的姓名，现在还没有同步把这个用户名下的所有歌单的创建者姓名同步更改过来
     */
    @PostMapping("/edit_personal_info")
    public JSONObject editInfo(@RequestBody UserEditForm userEditForm) {
        User user = userService.editUser(userEditForm);
        JSONObject ret = util.userInfo2Json(user);
        return ret;
    }

}
