package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.KeyArtists;
import cn.edu.zucc.syx.rec.entity.KeySong;
import cn.edu.zucc.syx.rec.impl.CollectServiceImpl;
import cn.edu.zucc.syx.rec.util.JsonUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
@RequestMapping("/api/collection")
public class CollectionController {
    @Autowired
    private CollectServiceImpl collectService;

    private JsonUtil util = new JsonUtil();

    @GetMapping("/songs/{host}")
    public JSONObject listSongsCollection(@PathVariable("host") String host){
        List<KeySong> keySongList = collectService.listSongsCollection(host);
        JSONObject ret = new JSONObject();
        ret  = util.userSongs2Json(keySongList);
        return ret;
    }

    @GetMapping("/artists/{host}")
    public JSONObject listArtistsCollection(@PathVariable("host") String host){
        JSONObject ret = new JSONObject();
        List<KeyArtists> keyArtistsList = collectService.listArtistsCollection(host);
        ret  = util.userArtist2Json(keyArtistsList);
        return ret;
    }

    @PostMapping("/delete_song/{host}/{song_id}")
    public JSONObject deleteSong(@PathVariable("host") String host,@PathVariable("song_id") String song_id){

        JSONObject ret = new JSONObject();
        try {
            KeySong keySong = collectService.deleteSong(host, song_id);
            ret = util.userSong2Json(keySong);
        } catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "delete song failed");
        }
        return ret;
    }

    @PostMapping("/add_song/{host}/{song_id}")
    public JSONObject addSong(@PathVariable("host") String host, @PathVariable("song_id") String song_id){
        JSONObject ret = new JSONObject();
        try {
            KeySong keySong = collectService.addSong(host, song_id);
            ret = util.userSong2Json(keySong);
        } catch (Exception e){
            System.out.println(e);
            ret.put("code", "error");
            ret.put("msg", "add favorate song failed");
        }
        return ret;
    }

    @PostMapping("/add_artist/{host}/{artist_id}")
    public JSONObject addArtist(@PathVariable("host") String host, @PathVariable("artist_id") String artist_id){
        JSONObject ret = new JSONObject();
//        try {
            KeyArtists artist = collectService.addArtist(host, artist_id);
            System.out.println("11111");
            ret = util.userArtist2Json(artist);
//        } catch (Exception e){
//            ret.put("code", "error");
//            ret.put("msg", "add favorate artist failed");
//        }
        return ret;
    }
    @PostMapping("/delete_artist/{host}/{artist_id}")
    public JSONObject deleteArtist(@PathVariable("host") String host, @PathVariable("artist_id") String artist_id){
        JSONObject ret = new JSONObject();
        try {
            KeyArtists artist = collectService.deleteArtist(host, artist_id);
            ret = util.userArtist2Json(artist);
        } catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "add favorate artist failed");
        }
        return ret;
    }


}
