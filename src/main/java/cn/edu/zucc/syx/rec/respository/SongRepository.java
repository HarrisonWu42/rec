package cn.edu.zucc.syx.rec.respository;

import cn.edu.zucc.syx.rec.entity.Song;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SongRepository extends ElasticsearchRepository<Song,Integer> {
    Song queryById(String song_id);
    Song queryByName(String song_name);
}
