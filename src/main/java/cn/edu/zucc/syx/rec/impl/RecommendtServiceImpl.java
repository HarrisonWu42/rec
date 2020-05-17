package cn.edu.zucc.syx.rec.impl;


import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.respository.ArtistRepository;
import cn.edu.zucc.syx.rec.respository.SongRepository;
import cn.edu.zucc.syx.rec.respository.UserRepository;
import cn.edu.zucc.syx.rec.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendtServiceImpl implements RecommendService {
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    @Autowired
    public RecommendtServiceImpl(UserRepository userRepository, SongRepository songRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

//    @Override
////    public List<KeySong> listSongsCollection(String host) {
////        User user = userRepository.findUserByHost(host);
////        return user.getCollection().getSongs();
////    }
//
    @Override
    public List<KeySong> recommandSongByDl(String host) {
        User user = userRepository.findUserByHost(host);
        List<KeySong> keySongList = user.getCollection().getSongs();
        List<KeySong> recommendSongs = user.getCollection().getSongs();
        for(KeySong keySong : keySongList){
            Song song = songRepository.queryById(keySong.getSong_id());
            List<String> songlist = song.getSimilar_dl();
            for(String song_id: songlist){
                Song songTmp = songRepository.queryById(song_id);
                KeySong tmpKeySong = new KeySong();
                tmpKeySong.setSong_id(song_id);
                tmpKeySong.setSong_name(song.getName());
                tmpKeySong.setArtist_name(song.getArtist_name());
                tmpKeySong.setRelease(song.getRelease());
                tmpKeySong.setArtist_id(song.getArtist_id());
                recommendSongs.add(keySong);
            }
        }
        return recommendSongs;
    }


}
