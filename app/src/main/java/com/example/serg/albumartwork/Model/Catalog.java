package com.example.serg.albumartwork.Model;

import java.util.List;
import java.util.Observable;

public class Catalog extends Observable {

    private List<Album> albums;

    public Catalog() {}

    public Catalog(List<Album> albums) {
        this.albums = albums;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
        modified();
    }

    public void setAlbumTracks(int albmNum, List<Track> tracks){
        this.albums.get(albmNum).setTrackList(tracks);
        setChanged();
        notifyObservers(albums.get(albmNum));
    }

    public void setCatalog(Catalog catalog){
        this.setAlbums(catalog.getAlbums());
    }

    private void modified() {
        setChanged();
        notifyObservers(this);
    }
}
