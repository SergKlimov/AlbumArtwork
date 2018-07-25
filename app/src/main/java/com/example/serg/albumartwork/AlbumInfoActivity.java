package com.example.serg.albumartwork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.serg.albumartwork.Dagger.Module.GlideRequests;
import com.example.serg.albumartwork.Model.Catalog;
import com.example.serg.albumartwork.Presenter.AlbumInfoPresenter;
import com.example.serg.albumartwork.Presenter.IAlbumInfoPresenter;
import com.example.serg.albumartwork.View.AlbumInfoView;
import com.example.serg.albumartwork.View.IAlbumInfoView;

import javax.inject.Inject;

public class AlbumInfoActivity extends AppCompatActivity implements LayoutManagerProvider {

    private IAlbumInfoView albumInfoView;
    private IAlbumInfoPresenter albumInfoPresenter;
    @Inject Catalog catalog;
    @Inject GlideRequests glideRequests;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);
        albumInfoView = new AlbumInfoView(
                (AppCompatTextView)findViewById(R.id.album_name),
                (AppCompatTextView)findViewById(R.id.album_artist),
                //(AppCompatTextView)findViewById(R.id.album_tracks_count),
                (AppCompatTextView)findViewById(R.id.album_genre),
                //(AppCompatTextView)findViewById(R.id.album_release_date),
                (AppCompatImageView)findViewById(R.id.album_cover),
                (RecyclerView)findViewById(R.id.tracks_recycler)
        );
        catalog = ArtworkApplication.getComponent().getCatalog();
        glideRequests = ArtworkApplication.getComponent().getGldie();
        albumInfoPresenter = new AlbumInfoPresenter(albumInfoView, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        catalog.addObserver(albumInfoPresenter);
        int albumNum;
        Bundle bundle = getIntent().getBundleExtra(AppResources.ALBUM_NUMBER_BUNDLE);
        if(null != bundle){
            albumNum = bundle.getInt(AppResources.ALBUM_NUMBER);
            albumInfoPresenter.updateAlbumInfo(catalog.getAlbums().get(albumNum));
        }else {
            Log.d("AlbumInfoActivity", "NOOOOOO!");
        }
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
