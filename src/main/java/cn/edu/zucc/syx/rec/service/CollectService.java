package cn.edu.zucc.syx.rec.service;

import cn.edu.zucc.syx.rec.entity.KeyArtists;
import cn.edu.zucc.syx.rec.entity.KeySong;

import java.util.List;

public interface CollectService {
    List<KeySong>  listSongsCollection(String  host);
    List<KeyArtists>  listArtistsCollection(String  host);
    KeySong deleteSong(String  host, String song_id);
    KeySong addSong(String  host, String song_id);
    KeyArtists deleteArtist(String  host, String artist_id);
    KeyArtists addArtist(String  host, String artist_id);
}
