package cn.edu.zucc.syx.rec.controller;

import cn.edu.zucc.syx.rec.entity.KeySong;
import cn.edu.zucc.syx.rec.impl.RecommendtServiceImpl;
import cn.edu.zucc.syx.rec.service.RecommendService;
import cn.edu.zucc.syx.rec.util.JsonUtil;
import cn.edu.zucc.syx.rec.util.PageUtil;
import cn.edu.zucc.syx.rec.view.ItemcfResult;
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
@RequestMapping("/api/rec")
public class RecommendController {

    private JsonUtil util = new JsonUtil();
    @Autowired
    private RecommendtServiceImpl recommendService;

    // 推荐1,deeplearning
    @GetMapping("/{host}/recommandbyDl")
    public JSONObject recommandSongByDl(@PathVariable("host") String host, @RequestParam("page_num") int pageNum,
                                        @RequestParam("page_size") int pageSize){
        JSONObject ret = new JSONObject();
        List<KeySong> recommendDLsongs = recommendService.recommandSongByDl(host);
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        Page<KeySong> page = PageUtil.createPageFromList(recommendDLsongs, pageable);
        ret = util.collectionSongPage2Json(page);

        return ret;
    }

    // 推荐2,itemcf
    @GetMapping("/{host}/recommandbyItemcf")
    public JSONObject recommandSongByItemcf(@PathVariable("host") String host, @RequestParam("page_num") int pageNum,
                                        @RequestParam("page_size") int pageSize){
        JSONObject ret = new JSONObject();
        List<ItemcfResult> recommendsongs = recommendService.recommandSongByItemcf(host);
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        Page<ItemcfResult> page = PageUtil.createPageFromList(recommendsongs, pageable);
        ret = util.recPage2Json(page);

        return ret;
    }
}
