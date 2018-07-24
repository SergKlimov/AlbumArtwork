package com.example.serg.albumartwork.View;

import android.support.v7.widget.AppCompatImageView;

public interface IAlbumView {
    //void setCover(String cover);
    void setName(String albumName);
    void setYear(String year);
    void setTracksCount(String tracksCount);
    AppCompatImageView getCover();
    /*String getName();
    String getYear();
    String getTracksCount();*/

    IAlbumView getInstance();
}
