package cn.edu.zucc.syx.rec.impl;


import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.respository.ArtistRepository;
import cn.edu.zucc.syx.rec.respository.SongRepository;
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

    @Override
    public KeySong deleteSong(String host, String song_id) {
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
        userCollection.setSongs(keySongList);
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.source("collection", userCollection);
        UpdateQuery updateQuery = new UpdateQueryBuilder().withId(user.getHost()).withClass(User.class).withIndexRequest(indexRequest).build();
        elasticsearchTemplate.update(updateQuery);
        return keySong;
    }
    @Override
    public KeyArtists deleteArtist(String host, String artist_id) {
        User user = userRepository.findUserByHost(host);
        UserCollection userCollection = user.getCollection();
        List<KeyArtists> keyArtistsList = userCollection.getArtists();
//        keySongList.removeIf(song -> song.getSong_id().equals(song_id));
        KeyArtists deleted_artist = null;
        for(KeyArtists artist :keyArtistsList){
            if(artist.getArtist_id().equals(artist_id)){
                deleted_artist = artist;
                keyArtistsList.remove(artist);
            }
        }
        userCollection.setArtists(keyArtistsList);
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.source("collection", userCollection);
        UpdateQuery updateQuery = new UpdateQueryBuilder().withId(user.getHost()).withClass(User.class).withIndexRequest(indexRequest).build();
        elasticsearchTemplate.update(updateQuery);
        return deleted_artist;
    }

    @Override
    public KeyArtists addArtist(String host, String artist_id) {
        User user = userRepository.findUserByHost(host);
        UserCollection userCollection = user.getCollection();
        List<KeyArtists> keyArtistsList = userCollection.getArtists();
//        keySongList.removeIf(song -> song.getSong_id().equals(song_id));
        Artist artist =  artistRepository.queryById(artist_id);
        KeyArtists add_artist = null;
//        for(KeyArtists artist :keyArtistsList){
//            if(artist.getArtist_id().equals(artist_id)){
//                deleted_artist = artist;
//                keyArtistsList.remove(artist);
//            }
//        }
        add_artist.setArtist_id(artist.getId());
        add_artist.setArtist_name(artist.getName());
        keyArtistsList.add(add_artist);
        userCollection.setArtists(keyArtistsList);
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.source("collection", userCollection);
        UpdateQuery updateQuery = new UpdateQueryBuilder()
                .withId(user.getHost())
                .withClass(User.class)
                .withIndexRequest(indexRequest)
                .build();
        elasticsearchTemplate.update(updateQuery);
        return add_artist;
    }
}
