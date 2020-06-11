package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.KeySong;
import cn.edu.zucc.syx.rec.entity.Sheet;
import cn.edu.zucc.syx.rec.entity.UserSheets;
import cn.edu.zucc.syx.rec.impl.CollectServiceImpl;
import cn.edu.zucc.syx.rec.impl.SheetServiceImpl;
import cn.edu.zucc.syx.rec.impl.UserServiceImpl;
import cn.edu.zucc.syx.rec.service.CollectService;
import cn.edu.zucc.syx.rec.util.JsonUtil;
import cn.edu.zucc.syx.rec.util.PageUtil;
import cn.edu.zucc.syx.rec.util.Statue;
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
@RequestMapping("/api/sheet")
public class SheetController {
    @Autowired
    private SheetServiceImpl sheetService;

    private JsonUtil util = new JsonUtil();

    /**
     * 创建歌单
     */
    @PostMapping("/{host}/create")
    public JSONObject createSheet(@PathVariable String host,
                                  @RequestParam("sheet_name") String sheetName,
                                  @RequestParam("description") String description){
        JSONObject ret = new JSONObject();
        Sheet sheet = sheetService.create(sheetName, description, host);
        ret = util.sheetBrief2Json(sheet);

        return ret;
    }

    /**
     * 删除歌单
     */
    @GetMapping("/{host}/delete")
    public JSONObject delete(@PathVariable String host, @RequestParam("sheet_id") String sheetId){
        JSONObject ret = new JSONObject();
        Sheet sheet = sheetService.delete(host, sheetId);
        ret = util.sheetBrief2Json(sheet);

        return ret;
    }

    /**
     * 查询用户的所有歌单
     */
    @GetMapping("/{host}/list")
    public JSONObject listAll(@PathVariable String host){
        JSONObject ret = new JSONObject();
        List<UserSheets> sheetsList = sheetService.listAll(host);
        ret = util.userSheets2Json(sheetsList);

        return ret;
    }

    /**
     * 添加歌曲到歌单
     */
    @PostMapping("/add_song")
    public JSONObject addSong(@RequestParam("sheet_id") String sheetId,
                              @RequestParam("song_id") String songId){
        JSONObject ret = new JSONObject();
        if (sheetService.isExist(sheetId, songId)){
            ret.put("code", "error");
            ret.put("msg", "exist");
            return ret;
        }

        Boolean ok = sheetService.addSong2Sheet(sheetId, songId);
        JSONObject tmp = new JSONObject();
        tmp.put("song_id", songId);
        tmp.put("sheet_id", sheetId);
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);

        return ret;
    }

    /**
     * 从歌单中删除歌曲
     */
    @GetMapping("delete_song")
    public JSONObject deleteSong(@RequestParam("sheet_id") String sheetId,
                                 @RequestParam("song_id") String songId){
        JSONObject ret = new JSONObject();

        Boolean ok = sheetService.deleteSongFromSheet(sheetId, songId);
        JSONObject tmp = new JSONObject();
        tmp.put("song_id", songId);
        tmp.put("sheet_id", sheetId);
        ret.put("code", Statue.SUCCESS);
        ret.put("data", tmp);

        return ret;
    }

    /**
     * 公开歌单
      */
    @PostMapping("/open")
    public JSONObject open(@RequestParam("sheet_id") String sheetId){
        JSONObject ret = new JSONObject();

        Boolean ok = sheetService.open(sheetId);
        ret.put("code", Statue.SUCCESS);

        return ret;
    }

    /**
     * 取消公开歌单
      */
    @PostMapping("/close")
    public JSONObject close(@RequestParam("sheet_id") String sheetId){
        JSONObject ret = new JSONObject();

        Boolean ok = sheetService.close(sheetId);
        ret.put("code", Statue.SUCCESS);

        return ret;
    }

    /**
     * 收藏他人公开的歌单
      */
    @PostMapping("/{host}/collect")
    public JSONObject collect(@PathVariable String host, @RequestParam("sheet_id") String sheetId){
        JSONObject ret = new JSONObject();

        Sheet sheet = sheetService.collect(host, sheetId);
        ret = util.sheetBrief2Json(sheet);

        return ret;
    }

    /**
     * 查看单详情(含分页)歌
     */
    @GetMapping("/get_info")
    public JSONObject getsth(@RequestParam("sheet_id") String sheetId,
                                @RequestParam("page_num") int pageNum,
                                @RequestParam("page_size") int pageSize){
        JSONObject ret = new JSONObject();

        Sheet sheet = sheetService.getInfo(sheetId);
        List<KeySong> songs = sheet.getSongs();
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        Page<KeySong> page = PageUtil.createPageFromList(songs, pageable);
        ret = util.sheetInfoPage2Json(sheet, page);

        return ret;
    }
}
