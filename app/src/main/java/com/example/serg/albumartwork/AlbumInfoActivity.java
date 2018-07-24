package com.example.serg.albumartwork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
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
            /*String albums = "";
            for (int i =0; i<catalog.getAlbums().get(albumNum).getTrackList().size();i++){
                albums+=catalog.getAlbums().get(albumNum).getTrackList().get(i).getTrackName();
                albums+="\n";
            }
            num.setText(albums);*/

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
