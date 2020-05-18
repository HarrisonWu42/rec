package cn.edu.zucc.syx.rec.util;

import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.view.SearchArtistResult;
import cn.edu.zucc.syx.rec.view.SearchSheetResult;
import cn.edu.zucc.syx.rec.view.SearchSongResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    public JSONObject user2JSON(User user){
        JSONObject tmp = new JSONObject();
        tmp.put("host", user.getHost());
        tmp.put("name", user.getName());

        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);
        return ret;
    }

    public JSONObject userInfo2Json(User user){
        JSONObject tmp = new JSONObject();
        tmp.put("host", user.getHost());
        tmp.put("name", user.getName());
        tmp.put("sex", user.getSex());
        tmp.put("age", user.getAge());
        tmp.put("email", user.getEmail());
        tmp.put("phone", user.getPhone());

        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);

        return ret;
    }

    public JSONObject userSongs2Json(List<KeySong> keySongList){
        JSONObject tmp = new JSONObject();
        tmp.put("songs",keySongList);
        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);
        return ret;
    }

    public JSONObject userSong2Json(KeySong keySong){
        JSONObject tmp = new JSONObject();
        tmp.put("song",keySong);
        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);
        return ret;
    }

    public JSONObject Songs2Json(List<Song> songList){
        JSONObject tmp = new JSONObject();
        tmp.put("songs",songList);
        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);
        return ret;
    }

    public JSONObject Sheets2Json(List<Sheet> sheetList){
        JSONObject tmp = new JSONObject();
        tmp.put("sheets",sheetList);
        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);
        return ret;
    }

    public JSONObject Sheet2Json(Sheet sheet){
        JSONObject tmp = new JSONObject();
        tmp.put("sheet",sheet);
        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);
        return ret;
    }

    public JSONObject Artistss2Json(List<Artist> artistList){
        JSONObject tmp = new JSONObject();
        tmp.put("songs",artistList);
        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);
        return ret;
    }

    public JSONObject userArtist2Json(KeyArtists keyArtists){
        JSONObject tmp = new JSONObject();
        tmp.put("artist",keyArtists);
        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);
        return ret;
    }

    public JSONObject userArtist2Json(List<KeyArtists> keyArtistsList){
        JSONObject tmp = new JSONObject();
        tmp.put("artists", keyArtistsList);
        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);
        return ret;
    }

    public JSONObject sheetBrief2Json(Sheet sheet){
        JSONObject tmp = new JSONObject();
        tmp.put("sheet_id", sheet.getId());
        tmp.put("sheet_name", sheet.getName());
        tmp.put("host", sheet.getCreator_id());
        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);
        return ret;

    }

    public JSONObject userSheets2Json(List<UserSheets> sheets){
        List<JSONObject> jsonSheets = new ArrayList<>();
        for (UserSheets s:sheets){
            JSONObject tmp = new JSONObject();
            tmp.put("sheet_id", s.getSheet_id());
            tmp.put("sheet_name", s.getSheet_name());
            jsonSheets.add(tmp);
        }
        JSONObject tmp = new JSONObject();
        tmp.put("sheets", jsonSheets);

        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);
        return ret;
    }

    public JSONObject sheetInfoPage2Json(Sheet sheet, Page<KeySong> page){
        JSONObject ret = new JSONObject();
        JSONObject tmp = new JSONObject();
        tmp.put("sheet_id", sheet.getId());
        tmp.put("sheet_name", sheet.getName());
        tmp.put("description", sheet.getDescription());
        tmp.put("page_total", page.getTotalPages());
        tmp.put("page_num", page.getNumber()+1);
        tmp.put("page_size", page.getSize());
        List<KeySong> songs = page.getContent();
        tmp.put("songs", songs);
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);

        return ret;
    }

    public JSONObject collectionSongPage2Json(Page<KeySong> page){
        JSONObject ret = new JSONObject();
        JSONObject tmp = new JSONObject();
        tmp.put("page_total", page.getTotalPages());
        tmp.put("page_num", page.getNumber()+1);
        tmp.put("page_size", page.getSize());
        List<KeySong> songs = page.getContent();
        tmp.put("songs", songs);
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);

        return ret;
    }

    public JSONObject collectionArtistPage2Json(Page<KeyArtists> page){
        JSONObject ret = new JSONObject();
        JSONObject tmp = new JSONObject();
        tmp.put("page_total", page.getTotalPages());
        tmp.put("page_num", page.getNumber()+1);
        tmp.put("page_size", page.getSize());
        List<KeyArtists> artists = page.getContent();
        tmp.put("artists", artists);
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);

        return ret;
    }

    public JSONObject searchSongPage2Json(Page<SearchSongResult> page){
        JSONObject tmp = new JSONObject();
        tmp.put("page_total", page.getTotalPages());
        tmp.put("page_num", page.getNumber()+1);
        tmp.put("page_size", page.getSize());
        List<SearchSongResult> songs = page.getContent();
        tmp.put("songs", songs);
        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);
        return ret;
    }

    public JSONObject searchSheetPage2Json(Page<SearchSheetResult> page){
        JSONObject tmp = new JSONObject();
        tmp.put("page_total", page.getTotalPages());
        tmp.put("page_num", page.getNumber()+1);
        tmp.put("page_size", page.getSize());
        List<SearchSheetResult> sheets = page.getContent();
        tmp.put("sheets", sheets);
        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);
        return ret;
    }

    public JSONObject searchArtistPage2Json(Page<SearchArtistResult> page){
        JSONObject tmp = new JSONObject();
        tmp.put("page_total", page.getTotalPages());
        tmp.put("page_num", page.getNumber()+1);
        tmp.put("page_size", page.getSize());
        List<SearchArtistResult> artists = page.getContent();
        tmp.put("artists", artists);
        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);
        return ret;
    }
}
