package cn.edu.zucc.syx.rec.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
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
