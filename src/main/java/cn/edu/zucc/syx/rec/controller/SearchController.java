package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.impl.ArtistServiceImpl;
import cn.edu.zucc.syx.rec.impl.SheetServiceImpl;
import cn.edu.zucc.syx.rec.impl.SongServiceImpl;
import cn.edu.zucc.syx.rec.impl.UserServiceImpl;
import cn.edu.zucc.syx.rec.util.JsonUtil;
import cn.edu.zucc.syx.rec.util.PageUtil;
import cn.edu.zucc.syx.rec.view.SearchArtistResult;
import cn.edu.zucc.syx.rec.view.SearchSheetResult;
import cn.edu.zucc.syx.rec.view.SearchSongResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://39.101.189.21:8888", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
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
            songResult.setPic_url(s.getPic_url());
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
        List<Sheet> sheetList =  sheetService.searchByName(sheetName);
        User user = userService.queryUser(host);
        List<UserSheets> userSheetsList = user.getCollection().getSheets();
        List<String> userSheets = new ArrayList<>();
        for (UserSheets us:userSheetsList){
            userSheets.add(us.getSheet_id());
        }

        List<SearchSheetResult> sheets = new ArrayList<>();
        for (Sheet s:sheetList){
            SearchSheetResult sheetResult = new SearchSheetResult();
            sheetResult.setCreator_id(s.getCreator_id());
            sheetResult.setCreator_name(s.getCreator_name());
            sheetResult.setDescripiton(s.getDescription());
            sheetResult.setSheet_id(s.getId());
            sheetResult.setSheet_name(s.getName());
            if (userSheets.contains(s.getId())){
                sheetResult.setIs_collected(true);
            }else{
                sheetResult.setIs_collected(false);
            }
            sheets.add(sheetResult);
        }

        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        Page<SearchSheetResult> page = PageUtil.createPageFromList(sheets, pageable);
        JSONObject ret = util.searchSheetPage2Json(page);
        return ret;
    }

    @GetMapping("/artists")
    public JSONObject searchArtist(@RequestParam("artists_name") String sheetName,
                                   @RequestParam("host") String host,
                                   @RequestParam("page_num") int pageNum,
                                   @RequestParam("page_size") int pageSize){
        List<Artist> artistList =  artistService.searchByName(sheetName);
        User user = userService.queryUser(host);
        List<KeyArtists> userArtistsList = user.getCollection().getArtists();

        List<String> userArtists = new ArrayList<>();
        for (KeyArtists ua:userArtistsList){
            userArtists.add(ua.getArtist_id());
        }

        List<SearchArtistResult> artists = new ArrayList<>();
        for (Artist a:artistList){
            SearchArtistResult artistResult = new SearchArtistResult();
            artistResult.setArtist_id(a.getId());
            artistResult.setArtist_name(a.getName());
            if (userArtists.contains(a.getId())){
                artistResult.setIs_collected(true);
            }else {
                artistResult.setIs_collected(false);
            }
            artists.add(artistResult);
        }
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        Page<SearchArtistResult> page = PageUtil.createPageFromList(artists, pageable);
        JSONObject ret = util.searchArtistPage2Json(page);
        return ret;
    }
}
