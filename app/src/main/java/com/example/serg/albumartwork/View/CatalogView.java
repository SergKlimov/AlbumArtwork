package com.example.serg.albumartwork.View;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class CatalogView implements ICatalogView{

    private RecyclerView albumsRecyclerView;
    private ProgressBar progressBar;
    private AppCompatTextView nothingFound;

    public CatalogView(RecyclerView albumsRecyclerView, ProgressBar progressBar, AppCompatTextView nothingFound) {
        this.albumsRecyclerView = albumsRecyclerView;
        this.progressBar = progressBar;
        this.nothingFound = nothingFound;
    }

    @Override
    public AppCompatTextView getNothingFound() {
        return nothingFound;
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
