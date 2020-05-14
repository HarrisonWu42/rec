package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.Sheet;
import cn.edu.zucc.syx.rec.impl.SheetServiceImpl;
import cn.edu.zucc.syx.rec.util.JsonUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sheet")
public class SheetController {
    @Autowired
    private SheetServiceImpl sheetService;

    private JsonUtil util = new JsonUtil();

    // 创建歌单
    @PostMapping("/create")
    public JSONObject createSheet(String sheetName, String description, String userId){
        JSONObject ret = new JSONObject();
        try {
            Sheet sheet = sheetService.create(sheetName, description, userId);
            ret = util.sheetBrief2Json(sheet);
        }catch (Exception e){
            ret.put("code", "error");
            ret.put("msg", "failed");
        }

        return ret;
    }

    // 删除歌单
    @GetMapping("delete")
    public JSONObject delete(){
        JSONObject ret = new JSONObject();
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
    @PostMapping("open")
    public JSONObject open(){
        JSONObject ret = new JSONObject();
        return ret;
    }

    // 取消公开歌单
    @PostMapping("close")
    public JSONObject close(){
        JSONObject ret = new JSONObject();
        return ret;
    }


    // 收藏他人公开的歌单
    @PostMapping("collect")
    public JSONObject collect(String sheet_id, String user_id){
        JSONObject ret = new JSONObject();
        return ret;
    }


}
