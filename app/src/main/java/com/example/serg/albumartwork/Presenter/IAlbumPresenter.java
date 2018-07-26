package com.example.serg.albumartwork.Presenter;

import android.view.View;

import com.example.serg.albumartwork.Model.Album;

public interface IAlbumPresenter {
    void updateAlbum(Album album, View.OnClickListener clickListener);
}
