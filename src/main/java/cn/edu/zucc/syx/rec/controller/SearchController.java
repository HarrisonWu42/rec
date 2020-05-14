package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.KeySong;
import cn.edu.zucc.syx.rec.entity.Song;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
//    @GetMapping("/songs/{host}")
//    public JSONObject listSongsCollection(@PathVariable("host") String host){
//        Song song =
//        JSONObject ret = new JSONObject();
//        ret  = util.userSongs2Json(keySongList);
//        return ret;
//    }
}
