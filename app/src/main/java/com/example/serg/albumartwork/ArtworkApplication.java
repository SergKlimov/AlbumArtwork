package com.example.serg.albumartwork;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.serg.albumartwork.Dagger.Component.DaggerMainComponent;
import com.example.serg.albumartwork.Dagger.Component.MainComponent;
import com.example.serg.albumartwork.Dagger.Module.GlideApp;
import com.example.serg.albumartwork.Dagger.Module.GlideRequests;
import com.example.serg.albumartwork.Dagger.Module.MainModule;
import com.example.serg.albumartwork.Model.Catalog;

public class ArtworkApplication extends Application {

    public Catalog getCatalog() {
        return catalog;
    }

    private Catalog catalog = new Catalog();
    private static MainComponent component;
    private GlideRequests glide;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build();
        glide = GlideApp.with(this);
    }

    public GlideRequests getGlide() {
        return glide;
    }

    public static MainComponent getComponent(){
        return component;
    }

    public boolean getConnectionStatus(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
