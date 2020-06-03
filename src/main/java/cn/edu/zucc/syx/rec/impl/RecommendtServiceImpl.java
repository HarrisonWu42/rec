package cn.edu.zucc.syx.rec.impl;


import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.respository.SongRepository;
import cn.edu.zucc.syx.rec.respository.UserRepository;
import cn.edu.zucc.syx.rec.service.RecommendService;
import cn.edu.zucc.syx.rec.view.ItemcfResult;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<ItemcfResult> recommandSongByItemcf(String host) {
        User user = userRepository.findUserByHost(host);
        List<RecordSong> recordSongs = user.getRecord().getSongs();     // 播放历史作为偏好
        List<ItemcfResult> recommendSongs = new ArrayList<>();
        if(recordSongs.isEmpty()){
            return recommendSongs;
        }

        Map<String, Integer> ru = new HashMap<>();  // item-playcount
        for (RecordSong r:recordSongs){
            ru.put(r.getSong_id(), r.getCnt());
        }

        Map<String, Float> p = new HashMap<>(); // 兴趣度
        Map<String, List<String>> reasonMap = new HashMap<>(); // 推荐理由

        for (Map.Entry<String, Integer> entry: ru.entrySet()){
            String song_id = entry.getKey();    // item(播放记录里面的)
            int r = entry.getValue();
            Song song = songRepository.queryById(song_id);

            List<Similar> items = song.getItemcf_w();
            for (Similar i:items){
                String itemid = i.getSong_id();
                float pi = r * i.getValue();
                List<String> reasons = new ArrayList<>();
                if(p.containsKey(itemid)){
                    reasons = reasonMap.get(itemid);
                    reasons.add(song_id);
                    pi = pi + p.get(itemid);
                    p.put(itemid, pi);
                }else {
                    reasons.add(song_id);
                    p.put(itemid, pi);
                }
                reasonMap.put(itemid, reasons);
            }
        }

        // 排序
        List<Map.Entry<String, Float>> list = new ArrayList<Map.Entry<String, Float>>(p.entrySet());
        list.sort(new Comparator<Map.Entry<String, Float>>() {
            public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        // 返回5*5
        int retNum = 25;
        for(Map.Entry<String, Float> i:list){
            if (retNum > 0){
                Song song = songRepository.queryById(i.getKey());
                ItemcfResult tmp = new ItemcfResult();
                tmp.setSong_id(song.getId());
                tmp.setSong_name(song.getName());
                tmp.setArtist_name(song.getArtist_name());
                tmp.setRelease(song.getRelease());
                tmp.setArtist_id(song.getArtist_id());
                tmp.setPic_url(song.getPic_url());
                List<String> reasons = reasonMap.get(song.getId());
                String recReason = "";
                for (String r:reasons){
                    int num = ru.get(r);
                    recReason = recReason + "播放" + songRepository.queryById(r).getName() + num + " 次，";
                }
                recReason = recReason.substring(0,recReason.length()-1);
                tmp.setReason(recReason);
                recommendSongs.add(tmp);
                retNum = retNum - 1;
            }
        }
        return recommendSongs;
    }
}
