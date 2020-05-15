package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.Sheet;
import cn.edu.zucc.syx.rec.entity.UserSheets;
import cn.edu.zucc.syx.rec.impl.SheetServiceImpl;
import cn.edu.zucc.syx.rec.impl.UserServiceImpl;
import cn.edu.zucc.syx.rec.util.JsonUtil;
import cn.edu.zucc.syx.rec.util.Statue;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
@RequestMapping("/api/sheet")
public class SheetController {
    @Autowired
    private SheetServiceImpl sheetService;
    @Autowired
    private UserServiceImpl userService;

    private JsonUtil util = new JsonUtil();

    // 创建歌单
    @PostMapping("/{host}/create")
    public JSONObject createSheet(@PathVariable String host,
                                  @RequestParam("sheet_name") String sheetName,
                                  String description){
        JSONObject ret = new JSONObject();
        try {
            Sheet sheet = sheetService.create(sheetName, description, host);
            ret = util.sheetBrief2Json(sheet);
        } catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }

        return ret;
    }

    // 删除歌单
    @GetMapping("/{host}/delete")
    public JSONObject delete(@PathVariable String host, @RequestParam("sheet_id") String sheetId){
        JSONObject ret = new JSONObject();
        try {
            Sheet sheet = sheetService.delete(host, sheetId);
            ret = util.sheetBrief2Json(sheet);
        } catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }
        return ret;
    }

    // 查看歌单详情，要写分页，还没开始写
    @GetMapping("/get_info")
    public JSONObject getInfo(String sheetId){
        JSONObject ret = new JSONObject();
        try {
            Sheet sheet = sheetService.getInfo(sheetId);
            ret.put("code", Statue.SUCCESS);
        }catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }
        return ret;
    }

    // 查询所有歌单
    @GetMapping("/{host}/list")
    public JSONObject listAll(@PathVariable String host){
        JSONObject ret = new JSONObject();
        try {
            List<UserSheets> sheetsList = sheetService.listAll(host);
            ret = util.userSheets2Json(sheetsList);
        }catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }
        return ret;
    }

    // 添加歌曲到歌单
    @PostMapping("/add_song")
    public JSONObject addSong(@RequestParam("sheet_id") String sheetId,
                              @RequestParam("song_id") String songId){
        JSONObject ret = new JSONObject();

        try {
            Boolean ok = sheetService.addSong2Sheet(sheetId, songId);
            JSONObject tmp = new JSONObject();
            tmp.put("song_id", songId);
            tmp.put("sheet_id", sheetId);
            ret.put("code", Statue.SUCCESS);
            ret.put("data", tmp);
        }catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }
        return ret;
    }

    // 从歌单中删除歌曲
    @GetMapping("delete_song")
    public JSONObject deleteSong(@RequestParam("sheet_id") String sheetId,
                                 @RequestParam("song_id") String songId){
        JSONObject ret = new JSONObject();
        try {
            Boolean ok = sheetService.deleteSongFromSheet(sheetId, songId);
            JSONObject tmp = new JSONObject();
            tmp.put("song_id", songId);
            tmp.put("sheet_id", sheetId);
            ret.put("code", Statue.SUCCESS);
            ret.put("data", tmp);
        }catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }
        return ret;
    }

    // 公开歌单
    @PostMapping("/open")
    public JSONObject open(@RequestParam("sheet_id") String sheetId){
        JSONObject ret = new JSONObject();
        try {
            Boolean ok = sheetService.open(sheetId);
            ret.put("code", Statue.SUCCESS);
        }catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }
        return ret;
    }

    // 取消公开歌单
    @PostMapping("/close")
    public JSONObject close(@RequestParam("sheet_id") String sheetId){
        JSONObject ret = new JSONObject();
        try {
            Boolean ok = sheetService.close(sheetId);
            ret.put("code", Statue.SUCCESS);
        }catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }
        return ret;
    }


    // 收藏他人公开的歌单
    @PostMapping("/{host}/collect")
    public JSONObject collect(@PathVariable String host, @RequestParam("sheet_id") String sheetId){
        JSONObject ret = new JSONObject();
        try {
            Sheet sheet = sheetService.collect(host, sheetId);
            ret = util.sheetBrief2Json(sheet);
        }catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }
        return ret;
    }


}
