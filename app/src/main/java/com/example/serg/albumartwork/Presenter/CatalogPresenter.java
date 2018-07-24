package com.example.serg.albumartwork.Presenter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.serg.albumartwork.LayoutManagerProvider;
import com.example.serg.albumartwork.Model.Album;
import com.example.serg.albumartwork.Model.Catalog;
import com.example.serg.albumartwork.R;
import com.example.serg.albumartwork.View.AlbumView;
import com.example.serg.albumartwork.View.ICatalogView;

import java.util.List;
import java.util.Observable;

public class CatalogPresenter implements ICatalogPresenter {

    private final ICatalogView catalogView;
    private final LayoutManagerProvider provider;
    //private final Activity activity;
    //private List<Album> albums;

    public CatalogPresenter(ICatalogView catalogView,/*, Activity activity, List<Album> albums*/LayoutManagerProvider provider) {
        this.catalogView= catalogView;
        //this.activity = activity;
        //this.albums = albums;
        this.provider = provider;
    }

    @Override
    public void updateCatalog(Catalog catalog){
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.activity);
        RecyclerView.LayoutManager layoutManager = provider.provideLayoutManger();
        catalogView.getAlbumsRecyclerView().setLayoutManager(layoutManager);
        catalogView.getAlbumsRecyclerView().setHasFixedSize(true);
        catalogView.getAlbumsRecyclerView().setAdapter(
                new AlbumsAdapter(catalog));
    }

    @Override
    public void update(Observable observable, Object o) {
        if (null != o && o instanceof Catalog){
            Catalog catalog = (Catalog) o;
            Log.d("CatPres", "Update!");
            updateCatalog(catalog);
        }
    }

    private static class AlbumsAdapter extends RecyclerView.Adapter<AlbumView>{

        List<Album> albums;

        public AlbumsAdapter(Catalog catalog) {
            this.albums = catalog.getAlbums();
        }

        @Override
        public AlbumView onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            AlbumView albumView = null;
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.album_preview, parent, false);
            albumView = new AlbumView(view,
                    (TextView)view.findViewById(R.id.albumName),
                    (TextView)view.findViewById(R.id.albumYear),
                    (TextView)view.findViewById(R.id.tracksCount),
                    (AppCompatImageView)view.findViewById(R.id.cover)
                    );
            return albumView;
        }

        @Override
        public void onBindViewHolder(AlbumView holder, int position) {
            IAlbumPresenter albumPresenter = new AlbumPresenter(holder);
            albumPresenter.updateAlbum(albums.get(position));
            Log.d("bindView", "pos: " + position);
        }

        @Override
        public int getItemCount() {
            return albums.size();
        }
    }
}
