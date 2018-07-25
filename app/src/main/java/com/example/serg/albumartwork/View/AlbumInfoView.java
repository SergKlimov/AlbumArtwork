package com.example.serg.albumartwork.View;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

public class AlbumInfoView implements IAlbumInfoView {

    private AppCompatTextView albumName;
    private AppCompatTextView albumArtist;
    //private AppCompatTextView tracksCount;
    private AppCompatTextView albumGenre;
    //private AppCompatTextView albumReleaseDate;
    private AppCompatImageView cover;
    private RecyclerView tracksRecyclerView;
    private ProgressBar progressBar;

    public AlbumInfoView(AppCompatTextView albumName, AppCompatTextView albumArtist, /*AppCompatTextView tracksCount,*/ AppCompatTextView albumGenre, /*AppCompatTextView albumReleaseDate,*/ AppCompatImageView cover, RecyclerView tracksRecyclerView, ProgressBar progressBar) {
        this.albumName = albumName;
        this.albumArtist = albumArtist;
        //this.tracksCount = tracksCount;
        this.albumGenre = albumGenre;
        //this.albumReleaseDate = albumReleaseDate;
        this.cover = cover;
        this.tracksRecyclerView = tracksRecyclerView;
        this.progressBar = progressBar;
    }

    /*public AlbumInfoView(AppCompatImageView cover, RecyclerView tracksRecyclerView) {
        this.cover = cover;
        this.tracksRecyclerView = tracksRecyclerView;
    }*/

    @Override
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public AppCompatImageView getCover() {
        return cover;
    }

    @Override
    public AppCompatTextView getAlbumName() {
        return albumName;
    }

    public void setAlbumName(AppCompatTextView albumName) {
        this.albumName = albumName;
    }

    @Override
    public AppCompatTextView getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(AppCompatTextView albumArtist) {
        this.albumArtist = albumArtist;
    }

    /*@Override
    public AppCompatTextView getTracksCount() {
        return tracksCount;
    }

    public void setTracksCount(AppCompatTextView tracksCount) {
        this.tracksCount = tracksCount;
    }*/

    @Override
    public AppCompatTextView getAlbumGenre() {
        return albumGenre;
    }

    public void setAlbumGenre(AppCompatTextView albumGenre) {
        this.albumGenre = albumGenre;
    }

    /*@Override
    public AppCompatTextView getAlbumReleaseDate() {
        return albumReleaseDate;
    }

    public void setAlbumReleaseDate(AppCompatTextView albumReleaseDate) {
        this.albumReleaseDate = albumReleaseDate;
    }*/

    @Override
    public RecyclerView getTracksRecyclerView() {
        return tracksRecyclerView;
    }

}
