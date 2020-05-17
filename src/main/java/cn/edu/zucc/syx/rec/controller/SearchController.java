package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.Artist;
import cn.edu.zucc.syx.rec.entity.Sheet;
import cn.edu.zucc.syx.rec.entity.Song;
import cn.edu.zucc.syx.rec.service.ArtistService;
import cn.edu.zucc.syx.rec.service.SheetService;
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
    @Autowired
    private SheetService sheetService;
    @Autowired
    private ArtistService artistService;

    private JsonUtil util = new JsonUtil();

    @GetMapping("/songs/{song_name}")
    public JSONObject searchSong(@PathVariable("song_name") String songName){
        List<Song> songList =  songService.searchByName(songName);
        JSONObject ret = new JSONObject();
        ret  = util.Songs2Json(songList);
        return ret;
    }

    @GetMapping("/sheets/{sheet_name}")
    public JSONObject searchSheet(@PathVariable("sheet_name") String sheetName){
        List<Sheet> sheetList =  sheetService.findByName(sheetName);
        JSONObject ret = new JSONObject();
        ret  = util.Sheets2Json(sheetList);
        return ret;
    }

    @GetMapping("/artists/{artist_name}")
    public JSONObject searchArtist(@PathVariable("sheet_name") String sheet_name){
        List<Artist> artistList =  artistService.findByArtistName(sheet_name);
        JSONObject ret = new JSONObject();
        ret  = util.Artistss2Json(artistList);
        return ret;
    }
}
