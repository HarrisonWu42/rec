package cn.edu.zucc.syx.rec.service;


import cn.edu.zucc.syx.rec.entity.Song;

import java.util.List;

public interface SongService {
//    public Song searchSongByid(String song_id);
    public List<Song> searchByName(String name);

}
