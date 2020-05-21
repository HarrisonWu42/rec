package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.KeySong;
import cn.edu.zucc.syx.rec.service.RecommendService;
import cn.edu.zucc.syx.rec.util.JsonUtil;
import cn.edu.zucc.syx.rec.util.PageUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
@RequestMapping("/api/rec")
public class RecommendController {

    private JsonUtil util = new JsonUtil();
    @Autowired
    private RecommendService recommendService;

    // 推荐1, deeplearning
    @GetMapping("/{host}/recommandbyDl")
    public JSONObject recommandSongByDl(@PathVariable("host") String host, @RequestParam("page_num") int pageNum,
                                        @RequestParam("page_size") int pageSize){
        JSONObject ret = new JSONObject();

        try{
            List<KeySong> recommendDLsongs = recommendService.recommandSongByDl(host);
            Pageable pageable = PageRequest.of(pageNum-1, pageSize);
            Page<KeySong> page = PageUtil.createPageFromList(recommendDLsongs, pageable);
            ret = util.collectionSongPage2Json(page);
        } catch (Exception e){
            System.out.println(e);
            ret.put("code", "error");
            ret.put("msg", "failed");
        }
        return ret;
    }
}
