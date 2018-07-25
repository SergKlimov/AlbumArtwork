package com.example.serg.albumartwork.View;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

public interface IAlbumInfoView {
    public RecyclerView getTracksRecyclerView();
    public AppCompatImageView getCover();
    public AppCompatTextView getAlbumArtist();
    //public AppCompatTextView getTracksCount();
    public AppCompatTextView getAlbumGenre();
    //public AppCompatTextView getAlbumReleaseDate();
    public AppCompatTextView getAlbumName();
    public ProgressBar getProgressBar();
}
