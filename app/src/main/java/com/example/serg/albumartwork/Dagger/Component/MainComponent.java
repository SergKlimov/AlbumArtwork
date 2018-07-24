package com.example.serg.albumartwork.Dagger.Component;

import android.view.View;

import com.example.serg.albumartwork.Dagger.Module.CatalogModule;
import com.example.serg.albumartwork.Dagger.Module.GlideRequests;
import com.example.serg.albumartwork.Dagger.Module.MainModule;
import com.example.serg.albumartwork.Dagger.Scope.PerApp;
import com.example.serg.albumartwork.Model.Catalog;

import dagger.Component;

@PerApp
@Component(
        modules = {
                MainModule.class,
                CatalogModule.class
        }
)

public interface MainComponent {
    //void inject(MainActivity mainActivity);
    Catalog getCatalog();
    GlideRequests getGldie();
    //View.OnClickListener showAlbumInfo();
}
