package com.example.serg.albumartwork.Presenter;

import com.example.serg.albumartwork.Model.Album;

import java.util.Observer;

public interface IAlbumInfoPresenter extends Observer{
    void updateAlbumInfo(Album album);
}
