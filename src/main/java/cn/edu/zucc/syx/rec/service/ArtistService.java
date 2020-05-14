package cn.edu.zucc.syx.rec.service;


import cn.edu.zucc.syx.rec.entity.Artist;
import cn.edu.zucc.syx.rec.entity.Sheet;

import java.util.List;

public interface ArtistService {
//    public String add(Sheet sheet);
    List<Artist> findByArtistName(String artistName);



}
