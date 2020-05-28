package cn.edu.zucc.syx.rec.impl;


import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.respository.ArtistRepository;
import cn.edu.zucc.syx.rec.respository.SongRepository;
import cn.edu.zucc.syx.rec.respository.UserRepository;
import cn.edu.zucc.syx.rec.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

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
        List<String> userSongs = new ArrayList<>();
        for (KeySong keySong:keySongList){
            userSongs.add(keySong.getSong_id());
        }
        for(KeySong keySong : keySongList){
            Song song = songRepository.queryById(keySong.getSong_id());
            List<String> songlist = song.getSimilar_dl();
            if(songlist==null) continue;
            for(int i =0 ; i < songlist.size();i++){
                String song_id = songlist.get(i);
                if(userSongs.contains(song_id)){
                    continue;
                }
                Song songTmp = songRepository.queryById(song_id);
                if(songTmp==null) continue;
                KeySong tmpKeySong = new KeySong();
                tmpKeySong.setSong_id(song_id);
                tmpKeySong.setSong_name(songTmp.getName());
                tmpKeySong.setArtist_name(songTmp.getArtist_name());
                tmpKeySong.setRelease(songTmp.getRelease());
                tmpKeySong.setArtist_id(songTmp.getArtist_id());
                tmpKeySong.setPic_url(songTmp.getPic_url());
                recommendSongs.add(tmpKeySong);
                if(i == songlist.size()-1){
                    break;
                }
            }
        }
//        UserRec userRec = user.getRec();
//        userRec.setSongs(recommendSongs);
//        user.setRec(userRec);
//        userRepository.save(user);
        return recommendSongs;
    }

    public List<KeySong> recommandSongByItemcf(String host) {
        User user = userRepository.findUserByHost(host);
        List<RecordSong> recordSongs = user.getRecord().getSongs();
        List<KeySong> recommendSongs = new ArrayList<>();
        if(recordSongs.isEmpty()){
            return recommendSongs;
        }

        Map<String, Integer> ru = new HashMap<>();
        for (RecordSong r:recordSongs){
            ru.put(r.getSong_id(), r.getCnt());
        }

        Map<String, Float> p = new HashMap<>();
        for (Map.Entry<String, Integer> entry: ru.entrySet()){
            String song_id = entry.getKey();
            int r = entry.getValue();
            Song song = songRepository.queryById(song_id);
            List<Similar> items = song.getItemcf_w();
            for (Similar i:items){
                String itemid = i.getSong_id();
                float pi = r * i.getValue();
                p.put(itemid, pi);
            }
        }

        List<Map.Entry<String, Float>> list = new ArrayList<Map.Entry<String, Float>>(p.entrySet());
        list.sort(new Comparator<Map.Entry<String, Float>>() {
            public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for(Map.Entry<String, Float> i:list){
            if (i.getValue() > 0.1){ // 阈值
                Song song = songRepository.queryById(i.getKey());

                KeySong tmpKeySong = new KeySong();
                tmpKeySong.setSong_id(song.getId());
                tmpKeySong.setSong_name(song.getName());
                tmpKeySong.setArtist_name(song.getArtist_name());
                tmpKeySong.setRelease(song.getRelease());
                tmpKeySong.setArtist_id(song.getArtist_id());
                tmpKeySong.setPic_url(song.getPic_url());
                recommendSongs.add(tmpKeySong);
            }
        }
        return recommendSongs;
    }
}
