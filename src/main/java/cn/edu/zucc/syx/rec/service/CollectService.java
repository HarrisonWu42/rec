package cn.edu.zucc.syx.rec.service;


import cn.edu.zucc.syx.rec.entity.KeyArtists;
import cn.edu.zucc.syx.rec.entity.KeySong;
import cn.edu.zucc.syx.rec.entity.User;
import cn.edu.zucc.syx.rec.entity.UserCollection;
import cn.edu.zucc.syx.rec.form.UserForm;

import java.util.List;

public interface CollectService {
    public List<KeySong>  listSongsCollection(String  host);
    public List<KeyArtists>  listArtistsCollection(String  host);
    public KeySong deleteSong(String  host, String song_id);
    public KeySong addSong(String  host, String song_id);
    public KeyArtists deleteArtist(String  host, String artist_id);
    public KeyArtists addArtist(String  host, String artist_id);
}
