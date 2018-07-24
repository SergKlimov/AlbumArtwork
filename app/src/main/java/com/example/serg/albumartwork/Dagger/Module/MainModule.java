package com.example.serg.albumartwork.Dagger.Module;

import android.app.Application;

import com.example.serg.albumartwork.ArtworkApplication;
import com.example.serg.albumartwork.Dagger.Scope.PerApp;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private ArtworkApplication app;

    public MainModule(ArtworkApplication app){
        this.app = app;
    }

    @Provides @PerApp
    ArtworkApplication provideArtworkApp(){
        return app;
    }

    @Provides @PerApp
    Application provideApp(ArtworkApplication app){
        return app;
    }

    @Provides
    GlideRequests provideGlide(ArtworkApplication app) {
        return app.getGlide();
    }
}
