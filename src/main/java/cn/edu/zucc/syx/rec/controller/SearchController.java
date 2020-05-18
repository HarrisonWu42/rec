package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.impl.ArtistServiceImpl;
import cn.edu.zucc.syx.rec.impl.SheetServiceImpl;
import cn.edu.zucc.syx.rec.impl.SongServiceImpl;
import cn.edu.zucc.syx.rec.impl.UserServiceImpl;
import cn.edu.zucc.syx.rec.util.JsonUtil;
import cn.edu.zucc.syx.rec.util.PageUtil;
import cn.edu.zucc.syx.rec.view.SearchSongResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
                                 @RequestParam("host") String host,
                                 @RequestParam("page_num") int pageNum,
                                 @RequestParam("page_size") int pageSize){
        List<Song> songList =  songService.searchByName(songName);
        User user = userService.queryUser(host);
        List<KeySong> userSongsList = user.getCollection().getSongs();

        List<String> userSongs = new ArrayList<>();
        for (KeySong us:userSongsList){
            userSongs.add(us.getSong_id());
        }

        List<SearchSongResult> songs = new ArrayList<>();
        for (Song s:songList){
            SearchSongResult songResult = new SearchSongResult();
            songResult.setSong_id(s.getId());
            songResult.setSong_name(s.getName());
            songResult.setArtist_id(s.getArtist_id());
            songResult.setArtist_name(s.getArtist_name());
            songResult.setRelease(s.getRelease());
            if (userSongs.contains(s.getId())){
                songResult.setIs_collected(true);
            }else {
                songResult.setIs_collected(false);
            }
            songs.add(songResult);
        }

        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        Page<SearchSongResult> page = PageUtil.createPageFromList(songs, pageable);
        JSONObject ret = util.searchSongPage2Json(page);
        return ret;
    }

    @GetMapping("/sheets")
    public JSONObject searchSheet(@RequestParam("sheet_name") String sheetName,
                                  @RequestParam("host") String host,
                                  @RequestParam("page_num") int pageNum,
                                  @RequestParam("page_size") int pageSize){
        List<Sheet> sheetList =  sheetService.findByName(sheetName);
        JSONObject ret = new JSONObject();
        ret  = util.Sheets2Json(sheetList);
        return ret;
    }

    @GetMapping("/artists")
    public JSONObject searchArtist(@RequestParam("artists_name") String sheetName,
                                   @RequestParam("host") String host,
                                   @RequestParam("page_num") int pageNum,
                                   @RequestParam("page_size") int pageSize){
        List<Artist> artistList =  artistService.findByArtistName(sheetName);
        JSONObject ret = new JSONObject();
        ret  = util.Artistss2Json(artistList);
        return ret;
    }
}
