package com.example.serg.albumartwork.View;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

public class AlbumInfoView implements IAlbumInfoView {

    private AppCompatTextView albumName;
    private AppCompatTextView albumArtist;
    private AppCompatTextView albumGenre;
    private AppCompatImageView cover;
    private RecyclerView tracksRecyclerView;
    private ProgressBar progressBar;

    public AlbumInfoView(AppCompatTextView albumName, AppCompatTextView albumArtist, AppCompatTextView albumGenre, AppCompatImageView cover, RecyclerView tracksRecyclerView, ProgressBar progressBar) {
        this.albumName = albumName;
        this.albumArtist = albumArtist;
        this.albumGenre = albumGenre;
        this.cover = cover;
        this.tracksRecyclerView = tracksRecyclerView;
        this.progressBar = progressBar;
    }

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

    @Override
    public AppCompatTextView getAlbumGenre() {
        return albumGenre;
    }

    public void setAlbumGenre(AppCompatTextView albumGenre) {
        this.albumGenre = albumGenre;
    }

    @Override
    public RecyclerView getTracksRecyclerView() {
        return tracksRecyclerView;
    }

}
