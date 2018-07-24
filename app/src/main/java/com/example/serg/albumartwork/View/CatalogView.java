package com.example.serg.albumartwork.View;

import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

public class CatalogView implements ICatalogView{

    private RecyclerView albumsRecyclerView;

    public CatalogView(RecyclerView albumsRecyclerView) {
        this.albumsRecyclerView = albumsRecyclerView;
    }

    @Override
    public RecyclerView getAlbumsRecyclerView() {
        return albumsRecyclerView;
    }
}
