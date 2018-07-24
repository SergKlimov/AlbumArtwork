package com.example.serg.albumartwork.Model;

public class Track {
    private String artistName;
    private String trackName;
    private String cover;
    private int durationMillis;

    public Track(String artistName, String trackName, String cover, int durationMillis) {
        this.artistName = artistName;
        this.trackName = trackName;
        this.cover = cover;
        this.durationMillis = durationMillis;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getDurationMillis() {
        return durationMillis;
    }

    public void setDurationMillis(int durationMillis) {
        this.durationMillis = durationMillis;
    }
}
