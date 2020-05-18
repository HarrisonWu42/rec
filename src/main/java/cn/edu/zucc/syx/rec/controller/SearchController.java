package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.impl.ArtistServiceImpl;
import cn.edu.zucc.syx.rec.impl.SheetServiceImpl;
import cn.edu.zucc.syx.rec.impl.SongServiceImpl;
import cn.edu.zucc.syx.rec.impl.UserServiceImpl;
import cn.edu.zucc.syx.rec.util.JsonUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private SongServiceImpl songService;
    @Autowired
    private SheetServiceImpl sheetService;
    @Autowired
    private ArtistServiceImpl artistService;
    @Autowired
    private UserServiceImpl userService;

    private JsonUtil util = new JsonUtil();

    @GetMapping("/songs")
    public JSONObject searchSong(@RequestParam("song_name") String songName,
                                 @RequestParam("host") String host){
        List<Song> songList =  songService.searchByName(songName);
        User user = userService.queryUser(host);
        List<KeySong> userSongsList = user.getCollection().getSongs();
        JSONObject ret = util.searchSongs2Json(songList, userSongsList);
        return ret;
    }

    @GetMapping("/sheets")
    public JSONObject searchSheet(@RequestParam("sheet_name") String sheetName){
        List<Sheet> sheetList =  sheetService.findByName(sheetName);
        JSONObject ret = new JSONObject();
        ret  = util.Sheets2Json(sheetList);
        return ret;
    }

    @GetMapping("/artists")
    public JSONObject searchArtist(@RequestParam("artists_name") String sheetName){
        List<Artist> artistList =  artistService.findByArtistName(sheetName);
        JSONObject ret = new JSONObject();
        ret  = util.Artistss2Json(artistList);
        return ret;
    }
}
