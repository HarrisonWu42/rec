package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.KeySong;
import cn.edu.zucc.syx.rec.entity.Sheet;
import cn.edu.zucc.syx.rec.service.SheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sheet")
public class SheetController {
    // 9个api

    @Autowired
    private SheetService sheetService;

    // 创建歌单
    @PostMapping("/create")
    public String createSheet(String sheetName, String description, String userId){
        Sheet sheet = new Sheet();

        sheet.setSheet_name(sheetName);
        sheet.setCreator_id(userId);
        sheet.setDescription(description);
//        sheet.setId(1);
//        sheet.setStatue(0);
        List<KeySong> list = new ArrayList();
        sheet.setSongs(list);

        sheet.setSongs(list);
        try {
            sheetService.add(sheet);
            return "success";
        }catch (Exception e) {
            return "fail";
        }
    }

    // 删除歌单
    @GetMapping("delete")
    public String delete(){
        return "";
    }

    // 查看歌单详情
    @GetMapping("/get_info")
    public String getInfo(String sheetId){
        return "success";
    }

    // 查询所有歌单
    @GetMapping("/list")
    public String listAll(){
        return "";
    }

    // 添加歌曲到歌单
    @PostMapping("/add_song")
    public String addSong(){
        return "";
    }


    // 从歌单中删除歌曲
    @GetMapping("delete_song")
    public String deleteSong(){
        return "";
    }

    // 公开歌单
    @PostMapping("open")
    public String open(){
        return "";
    }

    // 取消公开歌单
    @PostMapping("close")
    public String close(){
        return "";
    }


    // 收藏他人公开的歌单
    @PostMapping("collect")
    public String collect(String sheet_id, String user_id){
        return "";
    }




}
