package cn.edu.zucc.syx.rec.view;

public class SearchSongResult {
    private  String song_id;
    private String song_name;
    private String artist_id;
    private String artist_name;
    private String release;
    private Boolean is_collected;

    public String getSong_id() {
        return song_id;
    }

    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public void setIs_collected(Boolean is_collected) {
        this.is_collected = is_collected;
    }

    public Boolean getIs_collected() {
        return is_collected;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public SearchSongResult() {
    }

    public SearchSongResult(String song_id, String song_name, String artist_id, String artist_name, String release, Boolean is_collected) {
        this.song_id = song_id;
        this.song_name = song_name;
        this.artist_id = artist_id;
        this.artist_name = artist_name;
        this.release = release;
        this.is_collected = is_collected;
    }
}
