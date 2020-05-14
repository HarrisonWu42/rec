package cn.edu.zucc.syx.rec.util;

import cn.edu.zucc.syx.rec.entity.*;
import com.alibaba.fastjson.JSONObject;

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
        tmp.put("user_id", sheet.getCreator_id());
        JSONObject ret = new JSONObject();
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);
        return ret;

    }
//    public JSONObject Song2Json(Song song){
//        JSONObject tmp = new JSONObject();
//        tmp.put("songs",keySong);
//        JSONObject ret = new JSONObject();
//        ret.put("code", Statue.SUCCESS);
//        ret.put("data", tmp);
//        return ret;
//    }

}
