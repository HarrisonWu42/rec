package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.impl.CollectServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collection")
public class CollectionController {
    @Autowired
    private CollectServiceImpl collectService;

    @GetMapping("/collection/{host}")
    public JSONObject listCollection(@PathVariable("host") String host){
        JSONObject ret = new JSONObject();
        return ret;
    }

    @PostMapping("collection/{host}/delete_song")
    public JSONObject deleteSong(@PathVariable("host") String host){
        JSONObject ret = new JSONObject();
        return ret;
    }

    @PostMapping("collection/{host}/add_song")
    public JSONObject addSong(@PathVariable("host") String host){
        JSONObject ret = new JSONObject();
        return ret;
    }

}
