package cn.edu.zucc.syx.rec.impl;

import cn.edu.zucc.syx.rec.entity.Artist;
import cn.edu.zucc.syx.rec.entity.Sheet;
import cn.edu.zucc.syx.rec.respository.ArtistRepository;
import cn.edu.zucc.syx.rec.respository.SongRepository;
import cn.edu.zucc.syx.rec.service.ArtistService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }
    @Autowired
    private ElasticsearchTemplate esTemplate;
    @Override
    public List<Artist> searchByNameContain(String artistName) {
//        return artistRepository.queryByNameLike(artistName);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(matchQuery("title", articleTitle).minimumShouldMatch("75%"))
                .withQuery(QueryBuilders.wildcardQuery("name", artistName))
                .build();

        return esTemplate.queryForList(searchQuery, Artist.class);
    }

}
