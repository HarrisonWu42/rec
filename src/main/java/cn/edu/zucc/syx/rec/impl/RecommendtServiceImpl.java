package cn.edu.zucc.syx.rec.impl;


import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.respository.ArtistRepository;
import cn.edu.zucc.syx.rec.respository.SongRepository;
import cn.edu.zucc.syx.rec.respository.UserRepository;
import cn.edu.zucc.syx.rec.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<KeySong> recommendSongs = new ArrayList<>();
        if(keySongList.isEmpty()){
            return recommendSongs;
        }

        for(KeySong keySong : keySongList){
            Song song = songRepository.queryById(keySong.getSong_id());
            List<String> songlist = song.getSimilar_dl();
            for(int i =0 ; i < songlist.size();i++){
                String song_id = songlist.get(i);
                Song songTmp = songRepository.queryById(song_id);
                KeySong tmpKeySong = new KeySong();
                tmpKeySong.setSong_id(song_id);
                tmpKeySong.setSong_name(songTmp.getName());
                tmpKeySong.setArtist_name(songTmp.getArtist_name());
                tmpKeySong.setRelease(songTmp.getRelease());
                tmpKeySong.setArtist_id(songTmp.getArtist_id());
                tmpKeySong.setPic_url(songTmp.getPic_url());
                recommendSongs.add(keySong);
            }
        }
//        UserRec userRec = user.getRec();
//        userRec.setSongs(recommendSongs);
//        user.setRec(userRec);
//        userRepository.save(user);
        return recommendSongs;
    }


}
