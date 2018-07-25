package com.example.serg.albumartwork.Dagger.Module;

import com.example.serg.albumartwork.LayoutManagerProvider;
import com.example.serg.albumartwork.Presenter.AlbumInfoPresenter;
import com.example.serg.albumartwork.Presenter.IAlbumInfoPresenter;
import com.example.serg.albumartwork.View.IAlbumInfoView;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumInfoPresenterModule {

    private IAlbumInfoView albumInfoView;
    private LayoutManagerProvider provider;

    public AlbumInfoPresenterModule(IAlbumInfoView albumInfoView, LayoutManagerProvider provider) {
        this.albumInfoView = albumInfoView;
        this.provider = provider;
    }

    @Provides
    IAlbumInfoPresenter provideAlbumInfoPresenter(){
        return new AlbumInfoPresenter(albumInfoView, provider);
    }
}
