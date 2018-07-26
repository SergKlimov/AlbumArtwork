package com.example.serg.albumartwork.Presenter;

import com.example.serg.albumartwork.Model.Catalog;

import java.util.Observer;

public interface ICatalogPresenter extends Observer {
    void updateCatalog(Catalog catalog);
    void showProgress();
    void hideProgress();
    void showNoInternetSnack();
}
