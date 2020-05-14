package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.Sheet;
import cn.edu.zucc.syx.rec.impl.SheetServiceImpl;
import cn.edu.zucc.syx.rec.impl.UserServiceImpl;
import cn.edu.zucc.syx.rec.util.JsonUtil;
import cn.edu.zucc.syx.rec.util.Statue;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // 查看歌单详情
    @GetMapping("/get_info")
    public JSONObject getInfo(String sheetId){
        JSONObject ret = new JSONObject();
        return ret;
    }

    // 查询所有歌单
    @GetMapping("/list")
    public JSONObject listAll(){
        JSONObject ret = new JSONObject();
        return ret;
    }

    // 添加歌曲到歌单
    @PostMapping("/add_song")
    public JSONObject addSong(){
        JSONObject ret = new JSONObject();
        return ret;
    }


    // 从歌单中删除歌曲
    @GetMapping("delete_song")
    public JSONObject deleteSong(){
        JSONObject ret = new JSONObject();
        return ret;
    }

    // 公开歌单
    @PostMapping("/{host}/open")
    public JSONObject open(@PathVariable String host, @RequestParam("sheet_id") String sheetId){
        JSONObject ret = new JSONObject();
        try {
            Boolean ok = sheetService.open(host, sheetId);
            ret.put("code", Statue.SUCCESS);
        }catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }
        return ret;
    }

    // 取消公开歌单
    @PostMapping("/{host}/close")
    public JSONObject close(@PathVariable String host, @RequestParam("sheet_id") String sheetId){
        JSONObject ret = new JSONObject();
        try {
            Boolean ok = sheetService.close(host, sheetId);
            ret.put("code", Statue.SUCCESS);
        }catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }
        return ret;
    }


    // 收藏他人公开的歌单
    @PostMapping("collect")
    public JSONObject collect(String sheet_id, String user_id){
        JSONObject ret = new JSONObject();
        return ret;
    }


}
