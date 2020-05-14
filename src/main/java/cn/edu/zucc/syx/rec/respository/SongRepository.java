package cn.edu.zucc.syx.rec.respository;

import cn.edu.zucc.syx.rec.entity.Song;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface SongRepository extends ElasticsearchRepository<Song,Integer> {
    Song queryById(String song_id);
    List<Song> queryByNameLike(String song_name);
}
