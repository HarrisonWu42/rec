package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.KeyArtists;
import cn.edu.zucc.syx.rec.entity.KeySong;
import cn.edu.zucc.syx.rec.service.CollectService;
import cn.edu.zucc.syx.rec.service.RecommendService;
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
    private CollectService collectService;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private RecommendService recommandService;

    private JsonUtil util = new JsonUtil();

    /**
     * 查看收藏夹（歌曲）（待修改，分页）
     */
    @GetMapping("/{host}/songs")
    public JSONObject listSongsCollection(@PathVariable("host") String host){
        List<KeySong> keySongList = collectService.listSongsCollection(host);
        JSONObject ret  = util.userSongs2Json(keySongList);
        return ret;
    }

    @GetMapping("/{host}/recommandbyDl")
        public JSONObject recommandSongByDl(@PathVariable("host") String host){
            List<KeySong> recommendDLsongs = recommandService.recommandSongByDl(host);
            JSONObject ret  = util.userSongs2Json(recommendDLsongs);
            return ret;
    }

    /**
     * 查看收藏夹（歌手）(待修改，分页)
     */
    @GetMapping("/{host}/artists")
    public JSONObject listArtistsCollection(@PathVariable("host") String host){
        List<KeyArtists> keyArtistsList = collectService.listArtistsCollection(host);
        JSONObject ret  = util.userArtist2Json(keyArtistsList);
        return ret;
    }

    /**
     * 收藏歌曲
     */
    @PostMapping("/{host}/add_song")
    public JSONObject addSong(@PathVariable("host") String host,
                              @RequestParam("song_id") String songId){
        JSONObject ret = new JSONObject();
        try {
            KeySong keySong = collectService.addSong(host, songId);
            ret = util.userSong2Json(keySong);
        } catch (Exception e){
            System.out.println(e);
            ret.put("code", "error");
            ret.put("msg", "add favorate song failed");
        }
        return ret;
    }

    /**
     * 取消收藏歌曲
     */
    @PostMapping("/{host}/delete_song")
    public JSONObject deleteSong(@PathVariable("host") String host,
                                 @RequestParam("song_id") String songId){
        JSONObject ret = new JSONObject();
        try {
            KeySong keySong = collectService.deleteSong(host, songId);
            ret = util.userSong2Json(keySong);
        } catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }
        return ret;
    }

    /**
     * 收藏歌手
     */
    @PostMapping("/{host}/add_artist")
    public JSONObject addArtist(@PathVariable("host") String host,
                                @RequestParam("artist_id") String artistId){
        JSONObject ret = new JSONObject();
        try {
            KeyArtists artist = collectService.addArtist(host, artistId);
            ret = util.userArtist2Json(artist);
        } catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }
        return ret;
    }

    /**
     * 取消收藏歌手
     */
    @PostMapping("/{host}/delete_artist")
    public JSONObject deleteArtist(@PathVariable("host") String host,
                                   @RequestParam("artist_id") String artistId){
        JSONObject ret = new JSONObject();
        try {
            KeyArtists artist = collectService.deleteArtist(host, artistId);
            ret = util.userArtist2Json(artist);
        } catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }
        return ret;
    }


}
