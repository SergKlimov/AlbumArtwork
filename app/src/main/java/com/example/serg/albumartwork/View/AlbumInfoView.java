package com.example.serg.albumartwork.View;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;

public class AlbumInfoView implements IAlbumInfoView {

    public AppCompatImageView getCover() {
        return cover;
    }

    private AppCompatImageView cover;
    private RecyclerView tracksRecyclerView;

    @Override
    public RecyclerView getTracksRecyclerView() {
        return tracksRecyclerView;
    }

    public AlbumInfoView(AppCompatImageView cover, RecyclerView tracksRecyclerView) {
        this.cover = cover;
        this.tracksRecyclerView = tracksRecyclerView;
    }
}
