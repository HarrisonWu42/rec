package cn.edu.zucc.syx.rec.view;

public class SearchArtistResult {
    private String artist_id;
    private String artist_name;
    private Boolean is_collected;

    public SearchArtistResult() {
    }

    public SearchArtistResult(String artist_id, String artist_name, Boolean is_collected) {
        this.artist_id = artist_id;
        this.artist_name = artist_name;
        this.is_collected = is_collected;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public Boolean getIs_collected() {
        return is_collected;
    }

    public void setIs_collected(Boolean is_collected) {
        this.is_collected = is_collected;
    }
}
