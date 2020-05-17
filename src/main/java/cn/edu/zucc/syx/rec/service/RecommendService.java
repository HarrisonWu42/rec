package cn.edu.zucc.syx.rec.service;

import cn.edu.zucc.syx.rec.entity.KeySong;

import java.util.List;

public interface RecommendService {
    List<KeySong> recommandSongByDl(String  host);
}
