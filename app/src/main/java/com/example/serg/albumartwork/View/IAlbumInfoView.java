package com.example.serg.albumartwork.View;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

public interface IAlbumInfoView {
    RecyclerView getTracksRecyclerView();
    AppCompatImageView getCover();
    AppCompatTextView getAlbumArtist();
    AppCompatTextView getAlbumGenre();
    AppCompatTextView getAlbumName();
    ProgressBar getProgressBar();
}
