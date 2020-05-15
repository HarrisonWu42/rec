package cn.edu.zucc.syx.rec.respository;


import cn.edu.zucc.syx.rec.entity.Artist;
import cn.edu.zucc.syx.rec.entity.Sheet;
import cn.edu.zucc.syx.rec.entity.Song;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArtistRepository extends ElasticsearchRepository<Artist,Integer> {
//    public Sheet
    List<Artist> queryByNameLike(String artist_name);
    Artist queryById(String artist_name);
}

