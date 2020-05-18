package cn.edu.zucc.syx.rec.impl;

import cn.edu.zucc.syx.rec.entity.Artist;
import cn.edu.zucc.syx.rec.respository.ArtistRepository;
import cn.edu.zucc.syx.rec.respository.SongRepository;
import cn.edu.zucc.syx.rec.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> searchByName(String artistName) {
//        return artistRepository.queryByNameLike(artistName);
        return artistRepository.queryByNameContains(artistName);
    }

}
