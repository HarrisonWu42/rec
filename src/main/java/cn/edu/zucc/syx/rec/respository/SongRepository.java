package cn.edu.zucc.syx.rec.respository;

import cn.edu.zucc.syx.rec.entity.Song;
import cn.edu.zucc.syx.rec.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SongRepository extends ElasticsearchRepository<Song,Integer> {
    Song queryBySong_id(String song_id);
}
