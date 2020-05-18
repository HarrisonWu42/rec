package cn.edu.zucc.syx.rec.service;

import cn.edu.zucc.syx.rec.entity.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> searchByName(String artistName);



}
