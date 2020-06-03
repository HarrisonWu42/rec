package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.form.SearchSongForm;
import cn.edu.zucc.syx.rec.impl.*;
import cn.edu.zucc.syx.rec.respository.SongRepository;
import cn.edu.zucc.syx.rec.service.PrankService;
import cn.edu.zucc.syx.rec.util.JsonUtil;
import cn.edu.zucc.syx.rec.util.PageUtil;
import cn.edu.zucc.syx.rec.view.SearchArtistResult;
import cn.edu.zucc.syx.rec.view.SearchLyricResult;
import cn.edu.zucc.syx.rec.view.SearchSheetResult;
import cn.edu.zucc.syx.rec.view.SearchSongResult;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WrapperQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@CrossOrigin(origins = "http://39.101.189.21:8888", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired(required=false)
    private ElasticsearchTemplate esTemplate;
    @Autowired
    private SongServiceImpl songService;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private SheetServiceImpl sheetService;
    @Autowired
    private ArtistServiceImpl artistService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PrankServiceImpl prankService;

    private JsonUtil util = new JsonUtil();

    @GetMapping("/songs")
    public JSONObject searchSong(SearchSongForm form){
        List<Song> songList =  songService.searchByName(form.getSongName());
        User user = userService.queryUser(form.getHost());
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

        Pageable pageable = PageRequest.of(form.getPageNum()-1, form.getPageSize());
        Page<SearchSongResult> page = PageUtil.createPageFromList(songs, pageable);
        JSONObject ret = util.searchSongPage2Json(page);
        return ret;
    }
    @GetMapping("/song_type")
    public JSONObject searchSongType(@RequestParam("song_type") String tag,
                                     @RequestParam("host") String host,
                                 @RequestParam("page_num") int pageNum,
                                 @RequestParam("page_size") int pageSize){
        Prank prank =  prankService.searchByTag(tag);
        List<KeySong> keySongList = prank.getSongs();
        User user = userService.queryUser(host);
        List<KeySong> userSongsList = user.getCollection().getSongs();

        List<String> userSongs = new ArrayList<>();
        for (KeySong us:userSongsList){
            userSongs.add(us.getSong_id());
        }
        List<SearchSongResult> songs = new ArrayList<>();
        for (KeySong s:keySongList){
            SearchSongResult songResult = new SearchSongResult();
            songResult.setSong_id(s.getSong_id());
            songResult.setSong_name(s.getSong_name());
            songResult.setArtist_id(s.getArtist_id());
            songResult.setArtist_name(s.getArtist_name());
            songResult.setRelease(s.getRelease());
            songResult.setPic_url(s.getPic_url());
//            songResult.setLyric();
            if (userSongs.contains(s.getSong_id())){
                songResult.setIs_collected(true);
            }else {
                songResult.setIs_collected(false);
            }
            songs.add(songResult);
        }
//        List<SearchSongResult> songs = new ArrayList<>();
//        for (KeySong s:keySongList){
//            SearchSongResult songResult = new SearchSongResult();
//            songResult.setSong_id(s.getSong_id());
//            songResult.setSong_name(s.getSong_name());
//            songResult.setArtist_id(s.getArtist_id());
//            songResult.setArtist_name(s.getArtist_name());
//            songResult.setRelease(s.getRelease());
//            songResult.setPic_url(s.getPic_url());
//
//            songs.add(songResult);
//        }

        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        Page<SearchSongResult> page = PageUtil.createPageFromList(songs, pageable);
        JSONObject ret = util.searchSongPage2Json(page);
        return ret;
    }
    @GetMapping("/lyrics1")
    public JSONObject searchSongLric1(@RequestParam("lyric") String lyric,
                                 @RequestParam("host") String host,
                                 @RequestParam("page_num") int pageNum,
                                 @RequestParam("page_size") int pageSize){

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(matchQuery("title", articleTitle).minimumShouldMatch("75%"))
                .withQuery(QueryBuilders.matchQuery("lyric", lyric).minimumShouldMatch("75%"))
                .build();

        List<Song> songList = esTemplate.queryForList(searchQuery, Song.class);
        User user = userService.queryUser(host);
        List<KeySong> userSongsList = user.getCollection().getSongs();

        List<String> userSongs = new ArrayList<>();
        for (KeySong us:userSongsList){
            userSongs.add(us.getSong_id());
        }

        List<SearchLyricResult> songs = new ArrayList<>();
        for (Song s:songList){
            SearchLyricResult songResult = new SearchLyricResult();
            songResult.setSong_id(s.getId());
            songResult.setSong_name(s.getName());
            songResult.setArtist_id(s.getArtist_id());
            songResult.setArtist_name(s.getArtist_name());
            songResult.setRelease(s.getRelease());
            songResult.setPic_url(s.getPic_url());
//            songResult.setLyric();
            if (userSongs.contains(s.getId())){
                songResult.setIs_collected(true);
            }else {
                songResult.setIs_collected(false);
            }
            songs.add(songResult);
        }

        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        Page<SearchLyricResult> page = PageUtil.createPageFromList(songs, pageable);
        JSONObject ret = util.searchLyricPage2Json(page);
        return ret;
    }
    @GetMapping("/lyrics")
    public JSONObject searchSongLric(@RequestParam("lyric") String lyric,
                                     @RequestParam("host") String host,
                                     @RequestParam("page_num") int pageNum,
                                     @RequestParam("page_size") int pageSize){

        SearchRequestBuilder searchRequestBuilder;
        //        String query = " { \"query\":{\"match_all\" : {\"boost\" : 1.0}}}";
        String query = "{ \"match\": { \"lyric\": \""+lyric+"\" } }";
        WrapperQueryBuilder wrapperQueryBuilder = QueryBuilders.wrapperQuery(query);
        searchRequestBuilder = esTemplate.getClient().prepareSearch("song");
        searchRequestBuilder.setQuery(QueryBuilders.wrapperQuery(query));
        SearchResponse response = searchRequestBuilder.execute().actionGet();
        SearchHit[] result    = response.getHits().getHits();
        System.out.println(1);
        User user = userService.queryUser(host);
        List<KeySong> userSongsList = user.getCollection().getSongs();

        List<String> userSongs = new ArrayList<>();
        for (KeySong us:userSongsList){
            userSongs.add(us.getSong_id());
        }

        List<SearchLyricResult> songs = new ArrayList<>();
        for ( SearchHit searchHit: result){
            SearchLyricResult songResult = new SearchLyricResult();
            Map<String,Object> maphit  = searchHit.getSourceAsMap();
            songResult.setScore(searchHit.getScore());
            songResult.setSong_id((String) maphit.get("id"));
            songResult.setSong_name((String) maphit.get("name"));
            songResult.setArtist_id((String) maphit.get("artist_id"));
            songResult.setArtist_name((String) maphit.get("artist_name"));
            songResult.setRelease((String) maphit.get("release"));
            songResult.setPic_url((String) maphit.get("pic_url"));
//            songResult.setLyric();
            if (userSongs.contains((String) maphit.get("id"))){
                songResult.setIs_collected(true);
            }else {
                songResult.setIs_collected(false);
            }
            songs.add(songResult);
        }

        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        Page<SearchLyricResult> page = PageUtil.createPageFromList(songs, pageable);
        JSONObject ret = util.searchLyricPage2Json(page);
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
    @GetMapping("/ll")
    public Iterable<Song> fuzzyPersons(@RequestParam("ll") String a){
        String[] name = a.split(" ");
        QueryBuilder queryCondition = QueryBuilders.moreLikeThisQuery(name);
        return songRepository.search(queryCondition);
    }
}
