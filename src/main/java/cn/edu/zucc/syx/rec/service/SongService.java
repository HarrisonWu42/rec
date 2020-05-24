package cn.edu.zucc.syx.rec.service;


import cn.edu.zucc.syx.rec.entity.Song;

import java.util.List;

public interface SongService {
//    public Song searchSongByid(String song_id);
    List<Song> searchByName(String name);
    List<Song> searchByLric(String name);
    List<Song> searchByLric1(String name);

}
