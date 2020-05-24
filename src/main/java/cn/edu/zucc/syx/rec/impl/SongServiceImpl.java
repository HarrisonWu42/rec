package cn.edu.zucc.syx.rec.impl;

import cn.edu.zucc.syx.rec.entity.Song;
import cn.edu.zucc.syx.rec.respository.SongRepository;
import cn.edu.zucc.syx.rec.service.SongService;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    @Autowired
    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> searchByName(String name) {
        return songRepository.queryByNameContains(name);
    }
    @Override
    public List<Song> searchByLric(String name) {

//        return songRepository.queryByLyric(name);
        return songRepository.findSongByLyric(name);
    }
    @Override
    public List<Song> searchByLric1(String name) {
        QueryBuilder queryCondition = QueryBuilders.fuzzyQuery("lyric", name).fuzziness(Fuzziness.AUTO);
//        songRepository.search(queryCondition);
        return songRepository.queryByLyric(name);
    }


}
