package cn.edu.zucc.syx.rec.respository;

import cn.edu.zucc.syx.rec.entity.Song;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface SongRepository extends ElasticsearchRepository<Song, Integer> {
    Song queryById(String songId);
    Song findById(String songId);
    List<Song> queryByNameLike(String songName);
    List<Song> queryByNameContains(String songName);
}
