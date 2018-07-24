package com.example.serg.albumartwork.Model;

import java.util.List;

public class Album {

    private String name;
    private String cover;
    private String releaseDate;
    private String genre;
    private String artist;
    private List<String> trackList;
    private int tracksCount;

    public int getTracksCount() {
        return tracksCount;
    }

    public void setTracksCount(int tracksCount) {
        this.tracksCount = tracksCount;
    }

    public Album(String name, String cover, String releaseDate, String genre, String artist, List<String> trackList, int tracksCount) {
        this.name = name;
        this.cover = cover;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.artist = artist;
        this.trackList = trackList;
        this.tracksCount = tracksCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<String> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<String> trackList) {
        this.trackList = trackList;
    }
}
