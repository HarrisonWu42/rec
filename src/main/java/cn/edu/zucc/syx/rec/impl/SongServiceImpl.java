package cn.edu.zucc.syx.rec.impl;

import cn.edu.zucc.syx.rec.entity.Song;
import cn.edu.zucc.syx.rec.respository.SongRepository;
import cn.edu.zucc.syx.rec.service.SongService;
//import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Autowired
    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> searchByNameContain(String name) {
//        MatchQuery matchQuery = new MatchQuery(1);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(matchQuery("title", articleTitle).minimumShouldMatch("75%"))
                .withQuery(QueryBuilders.wildcardQuery("name", name))
                .build();
        return esTemplate.queryForList(searchQuery, Song.class);
//        return songRepository.findByName("*"+name+"*");
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
