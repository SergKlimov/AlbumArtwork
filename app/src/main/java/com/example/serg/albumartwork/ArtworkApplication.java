package com.example.serg.albumartwork;

import android.app.Application;
import android.content.Context;

import com.example.serg.albumartwork.Dagger.Component.DaggerMainComponent;
import com.example.serg.albumartwork.Dagger.Component.MainComponent;
import com.example.serg.albumartwork.Dagger.Module.GlideApp;
import com.example.serg.albumartwork.Dagger.Module.GlideRequests;
import com.example.serg.albumartwork.Dagger.Module.MainModule;
import com.example.serg.albumartwork.Model.Album;
import com.example.serg.albumartwork.Model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class ArtworkApplication extends Application {

    public Catalog getCatalog() {
        return catalog;
    }

    private Catalog catalog = null;
    private static MainComponent component;

    private GlideRequests glide;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build();
        catalog = generateCatalog();
        glide = GlideApp.with(this);
    }

    public GlideRequests getGlide() {
        return glide;
    }

    public static MainComponent getComponent(Context context){
        return ((ArtworkApplication)context.getApplicationContext()).component;
    }

    public static MainComponent getComponent(){
        return component;
    }

    private Catalog generateCatalog(){
        int n = 20;
        List<Album> albums = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            albums.add(i,
                    new Album("Album "+i,
                            ""+i,
                            "2018",
                            "Rock",
                            "serg", new ArrayList<String>(2),
                            10));
        }
        Catalog catalog = new Catalog(albums);
        return catalog;
    }
}
