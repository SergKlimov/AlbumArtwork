package com.example.serg.albumartwork.View;

import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.serg.albumartwork.Model.Album;

public interface ICatalogView {
    /*void setAlbums(Album[] albums);
    Album[] getAlbums();
    String getSearchQueue();*/
    //public LinearLayout getAlbumsLayout();
    public RecyclerView getAlbumsRecyclerView();
}
