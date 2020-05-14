package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.KeyArtists;
import cn.edu.zucc.syx.rec.entity.KeySong;
import cn.edu.zucc.syx.rec.entity.User;
import cn.edu.zucc.syx.rec.impl.CollectServiceImpl;
import cn.edu.zucc.syx.rec.service.CollectService;
import cn.edu.zucc.syx.rec.util.JsonUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collection")
public class CollectionController {
    @Autowired
    private CollectServiceImpl collectService;

    private JsonUtil util = new JsonUtil();

    @GetMapping("/collection/songs/{host}")
    public JSONObject listSongsCollection(@PathVariable("host") String host){
        List<KeySong> keySongList = collectService.listSongsCollection(host);
        JSONObject ret = new JSONObject();
        ret  = util.userSongs2Json(keySongList);
        return ret;
    }
    @GetMapping("/collection/artists/{host}")
    public JSONObject listArtistsCollection(@PathVariable("host") String host){
        JSONObject ret = new JSONObject();
        List<KeyArtists> keyArtistsList = collectService.listArtistsCollection(host);
        ret  = util.userArtist2Json(keyArtistsList);
        return ret;
    }

    @PostMapping("collection/delete_song/{host}/{song_id}")
    public JSONObject deleteSong(@PathVariable("host") String host,@PathVariable("song_id") String song_id){

        JSONObject ret = new JSONObject();
        try {
            KeySong keySong = collectService.delete(host, song_id);
            ret = util.userSong2Json(keySong);
        } catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "delete song failed");
        }
        return ret;
    }

    @PostMapping("collection/add_song/{host}/")
    public JSONObject addSong(@PathVariable("host") String host){
        JSONObject ret = new JSONObject();
        return ret;
    }

}
