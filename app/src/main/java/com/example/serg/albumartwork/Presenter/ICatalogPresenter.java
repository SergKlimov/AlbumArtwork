package com.example.serg.albumartwork.Presenter;

import com.example.serg.albumartwork.Model.Album;
import com.example.serg.albumartwork.Model.Catalog;

import java.util.List;
import java.util.Observer;

public interface ICatalogPresenter extends Observer {
    public void updateCatalog(Catalog catalog);
    public void showProgress();
    public void hideProgress();
    public void showNoInternetSnack();
}
