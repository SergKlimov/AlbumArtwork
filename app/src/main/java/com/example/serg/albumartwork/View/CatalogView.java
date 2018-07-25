package com.example.serg.albumartwork.View;

import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class CatalogView implements ICatalogView{

    private RecyclerView albumsRecyclerView;
    private ProgressBar progressBar;

    public CatalogView(RecyclerView albumsRecyclerView, ProgressBar progressBar) {
        this.albumsRecyclerView = albumsRecyclerView;
        this.progressBar = progressBar;
    }

    @Override
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public RecyclerView getAlbumsRecyclerView() {
        return albumsRecyclerView;
    }
}
