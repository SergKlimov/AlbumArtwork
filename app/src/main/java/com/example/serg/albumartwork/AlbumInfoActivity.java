package com.example.serg.albumartwork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.serg.albumartwork.Dagger.Component.AlbumInfoPresenterComponent;
import com.example.serg.albumartwork.Dagger.Component.DaggerAlbumInfoPresenterComponent;
import com.example.serg.albumartwork.Dagger.Module.AlbumInfoPresenterModule;
import com.example.serg.albumartwork.Dagger.Module.GlideRequests;
import com.example.serg.albumartwork.Model.Catalog;
import com.example.serg.albumartwork.Presenter.AlbumInfoPresenter;
import com.example.serg.albumartwork.Presenter.IAlbumInfoPresenter;
import com.example.serg.albumartwork.View.AlbumInfoView;
import com.example.serg.albumartwork.View.IAlbumInfoView;

import javax.inject.Inject;

public class AlbumInfoActivity extends AppCompatActivity implements LayoutManagerProvider {

    private IAlbumInfoView albumInfoView;
    private AlbumInfoPresenterComponent component;
    @Inject Catalog catalog;
    @Inject GlideRequests glideRequests;
    @Inject IAlbumInfoPresenter albumInfoPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        albumInfoView = new AlbumInfoView(
                (AppCompatTextView)findViewById(R.id.album_name),
                (AppCompatTextView)findViewById(R.id.album_artist),
                (AppCompatTextView)findViewById(R.id.album_genre),
                (AppCompatImageView)findViewById(R.id.album_cover),
                (RecyclerView)findViewById(R.id.tracks_recycler),
                (ProgressBar)findViewById(R.id.progress_bar)
        );
        component = DaggerAlbumInfoPresenterComponent.builder()
                .albumInfoPresenterModule(new AlbumInfoPresenterModule(albumInfoView, this))
                .build();
        albumInfoPresenter = component.getAlbumInfoPresenter();
        catalog = ArtworkApplication.getComponent().getCatalog();
        glideRequests = ArtworkApplication.getComponent().getGldie();
    }

    @Override
    protected void onResume() {
        super.onResume();
        catalog.addObserver(albumInfoPresenter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        catalog.deleteObserver(albumInfoPresenter);
    }

    @Override
    public RecyclerView.LayoutManager provideLayoutManger() {
        return new LinearLayoutManager(this);
    }
}
