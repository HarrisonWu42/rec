package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.KeyArtists;
import cn.edu.zucc.syx.rec.entity.KeySong;
import cn.edu.zucc.syx.rec.service.CollectService;
import cn.edu.zucc.syx.rec.service.RecommendService;
import cn.edu.zucc.syx.rec.util.JsonUtil;
import cn.edu.zucc.syx.rec.util.PageUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@CrossOrigin(origins = "http://39.101.189.21:8888", maxAge = 3600)
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
     * 查看收藏夹（歌曲）
     */
    @GetMapping("/{host}/songs")
    public JSONObject listSongsCollection(@PathVariable("host") String host,
                                          @RequestParam("page_num") int pageNum,
                                          @RequestParam("page_size") int pageSize){
        JSONObject ret = new JSONObject();

        try{
            List<KeySong> songs = collectService.listSongsCollection(host);
            Pageable pageable = PageRequest.of(pageNum-1, pageSize);
            Page<KeySong> page = PageUtil.createPageFromList(songs, pageable);
            ret = util.collectionSongPage2Json(page);
        } catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }

        return ret;
    }

    /**
     * 查看收藏夹（歌曲）(全部)
     */
    @GetMapping("/{host}/songsAll")
    public JSONObject listSongsCollectionAll(@PathVariable("host") String host){
        JSONObject ret = new JSONObject();

        try{
            List<KeySong> songs = collectService.listSongsCollection(host);
            ret = util.userSongs2Json(songs);
        } catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }

        return ret;
    }

    /**
     * 查看收藏夹（歌手）
     */
    @GetMapping("/{host}/artists")
    public JSONObject listArtistsCollection(@PathVariable("host") String host,
                                            @RequestParam("page_num") int pageNum,
                                            @RequestParam("page_size") int pageSize){
        JSONObject ret = new JSONObject();

        try{
            List<KeyArtists> artists = collectService.listArtistsCollection(host);
            Pageable pageable = PageRequest.of(pageNum-1, pageSize);
            Page<KeyArtists> page = PageUtil.createPageFromList(artists, pageable);
            ret = util.collectionArtistPage2Json(page);
        } catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }

        return ret;
    }

    /**
     * 收藏歌曲
     */
    @PostMapping("/{host}/add_song")
    public JSONObject addSong(@PathVariable("host") String host,
                              @RequestParam("song_id") String songId){
        JSONObject ret = new JSONObject();
        if (collectService.isSongExist(host, songId)){
            ret.put("code", "error");
            ret.put("msg", "exist");
            return ret;
        }

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

        if (collectService.isArtistExist(host, artistId)){
            ret.put("code", "error");
            ret.put("msg", "exist");
            return ret;
        }

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
