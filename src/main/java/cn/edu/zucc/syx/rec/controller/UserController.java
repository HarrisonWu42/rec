package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.User;
import cn.edu.zucc.syx.rec.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public String add(User user) throws Exception {
        try {
            userService.add(user);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }

    @RequestMapping("/login/{name}/{passwd}")
    public String Login(@PathVariable("name") String name, @PathVariable("passwd") String passwd) throws Exception {
        Map<String, String> ret = new HashMap<String, String>();
//        try {
            User user = userService.queryArticleByNameAndPasswd(name, passwd);
            if (user != null) {
                ret.put("type", "success");
                ret.put("msg", "登录成功！");
                ret.put("user_name", user.getName());
                ret.put("user_host", user.getHost());
                return JSONObject.toJSONString(ret);
            } else {
                ret.put("type", "error");
                ret.put("msg", "账号不存在！");
                return JSONObject.toJSONString(ret);
            }
//        } catch (Exception e) {
//            return "fail";
//        }return null;

    }

}
