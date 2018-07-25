package com.example.serg.albumartwork.Dagger.Module;

import com.example.serg.albumartwork.AlbumClicked;
import com.example.serg.albumartwork.LayoutManagerProvider;
import com.example.serg.albumartwork.Presenter.CatalogPresenter;
import com.example.serg.albumartwork.Presenter.ICatalogPresenter;
import com.example.serg.albumartwork.View.ICatalogView;

import dagger.Module;
import dagger.Provides;

@Module
public class CatalogPresModule {
    private final ICatalogView catalogView;
    private final LayoutManagerProvider provider;
    private AlbumClicked albumClicked;

    public CatalogPresModule(ICatalogView catalogView, LayoutManagerProvider provider, AlbumClicked albumClicked) {
        this.catalogView = catalogView;
        this.provider = provider;
        this.albumClicked = albumClicked;
    }

    @Provides
    ICatalogPresenter provideCatalogPresenter(){
        return new CatalogPresenter(catalogView, provider, albumClicked);
    }
}
