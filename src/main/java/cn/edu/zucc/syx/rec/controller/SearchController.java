package cn.edu.zucc.syx.rec.controller;


import cn.edu.zucc.syx.rec.entity.Song;
import cn.edu.zucc.syx.rec.impl.CollectServiceImpl;
import cn.edu.zucc.syx.rec.respository.SongRepository;
import cn.edu.zucc.syx.rec.service.SongService;
import cn.edu.zucc.syx.rec.util.JsonUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private SongService songService;

    private JsonUtil util = new JsonUtil();
    @GetMapping("/songs/{song_name}")
    public JSONObject searchSong(@PathVariable("song_name") String song_name){
        List<Song> songList =  songService.searchByName(song_name);
        JSONObject ret = new JSONObject();
        ret  = util.Songs2Json(songList);
        return ret;
    }
}
