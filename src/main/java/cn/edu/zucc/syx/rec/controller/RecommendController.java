package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.KeySong;
import cn.edu.zucc.syx.rec.service.RecommendService;
import cn.edu.zucc.syx.rec.util.JsonUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
@RequestMapping("/api/rec")
public class RecommendController {

    private JsonUtil util = new JsonUtil();
    @Autowired
    private RecommendService recommendService;

    // 推荐1, deeplearning
    @GetMapping("/{host}/recommandbyDl")
    public JSONObject recommandSongByDl(@PathVariable("host") String host){
        List<KeySong> recommendDLsongs = recommendService.recommandSongByDl(host);
        JSONObject ret  = util.userSongs2Json(recommendDLsongs);
        return ret;
    }
}
