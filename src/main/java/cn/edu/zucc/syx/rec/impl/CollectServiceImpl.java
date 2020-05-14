package cn.edu.zucc.syx.rec.impl;


import cn.edu.zucc.syx.rec.entity.KeyArtists;
import cn.edu.zucc.syx.rec.entity.KeySong;
import cn.edu.zucc.syx.rec.entity.User;
import cn.edu.zucc.syx.rec.entity.UserCollection;
import cn.edu.zucc.syx.rec.respository.UserRepository;
import cn.edu.zucc.syx.rec.service.CollectService;
import org.elasticsearch.action.index.IndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    private final ElasticsearchTemplate elasticsearchTemplate;
    private final UserRepository userRepository;
    @Autowired
    public CollectServiceImpl(ElasticsearchTemplate elasticsearchTemplate, UserRepository userRepository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public List<KeySong> listSongsCollection(String host) {
        User user = userRepository.findUserByHost(host);
        return user.getCollection().getSongs();
    }

    @Override
    public List<KeyArtists> listArtistsCollection(String host) {
        User user = userRepository.findUserByHost(host);
        return user.getCollection().getArtists();
    }

    @Override
    public KeySong delete(String host, String song_id) {
        User user = userRepository.findUserByHost(host);
        UserCollection userCollection = user.getCollection();
        List<KeySong> keySongList = userCollection.getSongs();
//        keySongList.removeIf(song -> song.getSong_id().equals(song_id));
        KeySong deleted_song = null;
        for(KeySong keySong :keySongList){
            if(keySong.getSong_id().equals(song_id)){
                deleted_song = keySong;
                keySongList.remove(keySong);
            }
        }
        userCollection.setSongs(keySongList);
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.source("collection", userCollection);
        UpdateQuery updateQuery = new UpdateQueryBuilder().withId(user.getHost()).withClass(User.class).withIndexRequest(indexRequest).build();
        elasticsearchTemplate.update(updateQuery);
        return deleted_song;
    }
}
