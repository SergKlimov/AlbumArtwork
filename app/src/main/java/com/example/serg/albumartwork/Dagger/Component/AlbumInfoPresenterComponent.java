package com.example.serg.albumartwork.Dagger.Component;

import com.example.serg.albumartwork.Dagger.Module.AlbumInfoPresenterModule;
import com.example.serg.albumartwork.Dagger.Module.MainModule;
import com.example.serg.albumartwork.Dagger.Scope.PerActivity;
import com.example.serg.albumartwork.Presenter.IAlbumInfoPresenter;

import dagger.Component;

@PerActivity
@Component(
        modules = {
                AlbumInfoPresenterModule.class,
                MainModule.class
        }
)
public interface AlbumInfoPresenterComponent {
    IAlbumInfoPresenter getAlbumInfoPresenter();
}
