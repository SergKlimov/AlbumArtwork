package com.example.serg.albumartwork;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

public class ArtworkApplication extends Application/* implements AlbumClicked */{

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
        //catalog = generateCatalog();
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = format.parse("2018-10-10");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Album> albums = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            albums.add(i,
                    new Album("Album "+i,
                            ""+i,
                            date,
                            "Rock",
                            "serg", new ArrayList<Track>(2),
                            10,
                            0));
        }
        Catalog catalog = new Catalog(albums);
        return catalog;
    }

    /*@Override
    public View.OnClickListener showAlbumInfo() {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int albumNum = (int) view.getTag(R.integer.albumNum);

                Intent i = new Intent(getApplicationContext(), iTunesAPIService.class);
                Bundle bundle = new Bundle();
                bundle.putInt(AppResources.ALBUM_NUMBER, albumNum);
                bundle.putString(AppResources.SERVICE_CMD, AppResources.SERVICE_GET_TRACKS);
                i.putExtra(AppResources.INTENT_BUNDLE, bundle);
                startService(i);
                *//*Intent showAlbumInfo = new Intent(getApplicationContext(), AlbumInfoActivity.class);
                showAlbumInfo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle b = new Bundle();
                b.putInt(AppResources.ALBUM_NUMBER, albumNum);
                showAlbumInfo.putExtra(AppResources.ALBUM_NUMBER_BUNDLE, b);
                startActivity(showAlbumInfo);*//*
            }
        };
        return clickListener;
    }*/
}
