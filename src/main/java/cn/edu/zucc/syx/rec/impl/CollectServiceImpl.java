package cn.edu.zucc.syx.rec.impl;


import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.respository.ArtistRepository;
import cn.edu.zucc.syx.rec.respository.SongRepository;
import cn.edu.zucc.syx.rec.respository.UserRepository;
import cn.edu.zucc.syx.rec.service.CollectService;
import org.elasticsearch.action.index.IndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectServiceImpl implements CollectService {
    private final ElasticsearchTemplate elasticsearchTemplate;
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public CollectServiceImpl(ElasticsearchTemplate elasticsearchTemplate, UserRepository userRepository, SongRepository songRepository, ArtistRepository artistRepository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
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

//    @Override
//    public Page<KeySong> listSongsCollection(String host, Pageable pageable) {
//        User user = userRepository.findUserByHost(host);
//
//
//        return ;
//    }
//
//    @Override
//    public Page<KeyArtists> listArtistsCollection(String host, Pageable pageable) {
//        return artistRepository.;
//    }

    public List<KeyArtists> ifExsitSong(String host) {
        User user = userRepository.findUserByHost(host);
        return user.getCollection().getArtists();
    }

    @Override
    public KeySong deleteSong(String host, String song_id) {
        User user = userRepository.findUserByHost(host);
        UserCollection userCollection = user.getCollection();
        List<KeySong> keySongList = userCollection.getSongs();
//        keySongList.removeIf(song -> song.getSong_id().equals(song_id));
        KeySong deleted_song = new KeySong();
        for(KeySong keySong :keySongList){
            if(keySong.getSong_id().equals(song_id)){
                deleted_song = keySong;
                keySongList.remove(keySong);
                break;
            }
        }
        userCollection.setSongs(keySongList);
//        IndexRequest indexRequest = new IndexRequest();
//        indexRequest.source("collection", userCollection);
//        UpdateQuery updateQuery = new UpdateQueryBuilder().withId(user.getHost()).withClass(User.class).withIndexRequest(indexRequest).build();
//        elasticsearchTemplate.update(updateQuery);
        user.setCollection(userCollection);
        userRepository.save(user);
        return deleted_song;
    }

    @Override
    public KeySong addSong(String host, String song_id) {
        User user = userRepository.findUserByHost(host);
        Song song  = songRepository.queryById(song_id);
        KeySong keySong  = new KeySong();
        keySong.setSong_id(song_id);
        keySong.setSong_name(song.getName());
        keySong.setArtist_name(song.getArtist_name());
        keySong.setRelease(song.getRelease());
        keySong.setArtist_id(song.getArtist_id());
        UserCollection userCollection = user.getCollection();
        List<KeySong> keySongList = userCollection.getSongs();
//        keySongList.removeIf(song -> song.getSong_id().equals(song_id));

        keySongList.add(keySong);
//        userCollection.setSongs(keySongList);
//        IndexRequest indexRequest = new IndexRequest();
//        indexRequest.source("collection", userCollection);
//        UpdateQuery updateQuery = new UpdateQueryBuilder().withId(user.getHost()).withClass(User.class).withIndexRequest(indexRequest).build();
//        elasticsearchTemplate.update(updateQuery);
        userCollection.setSongs(keySongList);
        user.setCollection(userCollection);
        userRepository.save(user);
        return keySong;
    }
    @Override
    public KeyArtists deleteArtist(String host, String artist_id) {
        User user = userRepository.findUserByHost(host);
        UserCollection userCollection = user.getCollection();
        List<KeyArtists> keyArtistsList = userCollection.getArtists();
//        keySongList.removeIf(song -> song.getSong_id().equals(song_id));
        KeyArtists deleted_artist = new KeyArtists();
        for(KeyArtists artist :keyArtistsList){
            if(artist.getArtist_id().equals(artist_id)){
                deleted_artist = artist;
                keyArtistsList.remove(artist);
                break;
            }
        }
        userCollection.setArtists(keyArtistsList);
//        IndexRequest indexRequest = new IndexRequest();
//        indexRequest.source("collection", userCollection);
//        UpdateQuery updateQuery = new UpdateQueryBuilder().withId(user.getHost()).withClass(User.class).withIndexRequest(indexRequest).build();
//        elasticsearchTemplate.update(updateQuery);
        user.setCollection(userCollection);
        userRepository.save(user);
        return deleted_artist;
    }

    @Override
    public KeyArtists addArtist(String host, String artist_id) {
        User user = userRepository.findUserByHost(host);
        UserCollection userCollection = user.getCollection();
        List<KeyArtists> keyArtistsList = userCollection.getArtists();
        Artist artist =  artistRepository.queryById(artist_id);
        KeyArtists add_artist = new KeyArtists();
        add_artist.setArtist_id(artist.getId());
        add_artist.setArtist_name(artist.getName());
        keyArtistsList.add(add_artist);
        userCollection.setArtists(keyArtistsList);
        user.setCollection(userCollection);
        userRepository.save(user);
//        System.out.println("44444444444");
//        IndexRequest indexRequest = new IndexRequest();
//        System.out.println("5555555555555");
//
//        System.out.println("111");
//        Map<String,Object> source = new HashMap<>();
//        source.put("collection",userCollection);
//        indexRequest.source(source);
//        UpdateQuery updateQuery = new UpdateQueryBuilder().withId(user.getHost()).withClass(User.class).withIndexRequest(indexRequest).build();
//        elasticsearchTemplate.update(updateQuery);
        return add_artist;
    }

}
