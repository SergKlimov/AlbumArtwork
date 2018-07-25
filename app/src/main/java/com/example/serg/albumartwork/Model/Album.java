package com.example.serg.albumartwork.Model;

import java.util.Date;
import java.util.List;

public class Album {

    private String name;
    private String cover;
    private Date releaseDate;
    private String genre;
    private String artist;
    private List<Track> trackList;
    private int tracksCount;
    private int iTunesId;

    public int getiTunesId() {
        return iTunesId;
    }

    public void setiTunesId(int iTunesId) {
        this.iTunesId = iTunesId;
    }

    public int getTracksCount() {
        return tracksCount;
    }

    public void setTracksCount(int tracksCount) {
        this.tracksCount = tracksCount;
    }

    public Album(String name, String cover, Date releaseDate, String genre, String artist, List<Track> trackList, int tracksCount, int iTunesId) {
        this.name = name;
        this.cover = cover;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.artist = artist;
        this.trackList = trackList;
        this.tracksCount = tracksCount;
        this.iTunesId = iTunesId;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
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

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }
}
