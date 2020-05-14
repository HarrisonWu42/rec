package cn.edu.zucc.syx.rec.respository;


import cn.edu.zucc.syx.rec.entity.Artist;
import cn.edu.zucc.syx.rec.entity.Sheet;
import cn.edu.zucc.syx.rec.entity.Song;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArtistRepository extends ElasticsearchRepository<Artist,Integer> {
//    public Sheet
    Artist queryById(String artist_id);
}

