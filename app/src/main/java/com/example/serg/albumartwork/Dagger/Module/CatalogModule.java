package com.example.serg.albumartwork.Dagger.Module;

import com.example.serg.albumartwork.ArtworkApplication;
import com.example.serg.albumartwork.Model.Catalog;

import dagger.Module;
import dagger.Provides;

@Module
public class CatalogModule {

    @Provides
    Catalog provideCatalog(ArtworkApplication app){
        return app.getCatalog();
    }
}
