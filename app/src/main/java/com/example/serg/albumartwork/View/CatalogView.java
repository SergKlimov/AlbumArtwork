package com.example.serg.albumartwork.View;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

public class CatalogView implements ICatalogView{

    private RecyclerView albumsRecyclerView;
    private ProgressBar progressBar;
    private AppCompatTextView nothingFound;
    private CoordinatorLayout mainLayout;

    public CatalogView(RecyclerView albumsRecyclerView, ProgressBar progressBar, AppCompatTextView nothingFound, CoordinatorLayout mainLayout) {
        this.albumsRecyclerView = albumsRecyclerView;
        this.progressBar = progressBar;
        this.nothingFound = nothingFound;
        this.mainLayout = mainLayout;
    }

    @Override
    public CoordinatorLayout getMainLayout() {
        return mainLayout;
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
