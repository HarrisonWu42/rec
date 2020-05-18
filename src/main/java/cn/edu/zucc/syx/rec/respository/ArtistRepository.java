package cn.edu.zucc.syx.rec.respository;

import cn.edu.zucc.syx.rec.entity.Artist;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArtistRepository extends ElasticsearchRepository<Artist,Integer> {
    List<Artist> queryByNameLike(String artistName);
    List<Artist> queryByNameContains(String artistName);

    Artist queryById(String artistName);
}

