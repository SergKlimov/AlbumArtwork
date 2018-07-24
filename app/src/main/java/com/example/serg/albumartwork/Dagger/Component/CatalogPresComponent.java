package com.example.serg.albumartwork.Dagger.Component;

import com.example.serg.albumartwork.Dagger.Module.CatalogPresModule;
import com.example.serg.albumartwork.Dagger.Scope.PerActivity;
import com.example.serg.albumartwork.Presenter.ICatalogPresenter;

import dagger.Component;

@PerActivity
@Component(
        modules = {
                CatalogPresModule.class
        }
)
public interface CatalogPresComponent {
    ICatalogPresenter getCatalogPresenter();
}
