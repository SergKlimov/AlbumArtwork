package com.example.serg.albumartwork;

import android.app.Application;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.view.View;

import com.android.volley.NetworkDispatcher;
import com.example.serg.albumartwork.Dagger.Component.DaggerMainComponent;
import com.example.serg.albumartwork.Dagger.Component.MainComponent;
import com.example.serg.albumartwork.Dagger.Module.GlideApp;
import com.example.serg.albumartwork.Dagger.Module.GlideRequests;
import com.example.serg.albumartwork.Dagger.Module.MainModule;
import com.example.serg.albumartwork.Model.Album;
import com.example.serg.albumartwork.Model.Catalog;
import com.example.serg.albumartwork.Model.Track;
import com.example.serg.albumartwork.iTunesAPI.iTunesAPIService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

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
