package com.example.serg.albumartwork.View;

import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

public class CatalogView implements ICatalogView{

    private RecyclerView albumsRecyclerView;
    //private LinearLayout albumsLayout;

    public CatalogView(RecyclerView albumsRecyclerView/*, LinearLayout albumsLayout*/) {
        this.albumsRecyclerView = albumsRecyclerView;
        //this.albumsLayout = albumsLayout;
    }

    @Override
    public RecyclerView getAlbumsRecyclerView() {
        return albumsRecyclerView;
    }

    /*@Override
    public LinearLayout getAlbumsLayout() {
        return albumsLayout;
    }*/
}
