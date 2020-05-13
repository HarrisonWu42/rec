package cn.edu.zucc.syx.rec.util;

import cn.edu.zucc.syx.rec.entity.User;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
    public JSONObject user2JSON(User user){
        JSONObject tmp = new JSONObject();
        tmp.put("host", user.getHost());
        tmp.put("name", user.getName());

        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);
        return ret;
    }

    public JSONObject userInfo2Json(User user){
        JSONObject tmp = new JSONObject();
        tmp.put("host", user.getHost());
        tmp.put("name", user.getName());
        tmp.put("sex", user.getSex());
        tmp.put("age", user.getAge());
        tmp.put("email", user.getEmail());
        tmp.put("phone", user.getPhone());

        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);

        return ret;
    }


}
