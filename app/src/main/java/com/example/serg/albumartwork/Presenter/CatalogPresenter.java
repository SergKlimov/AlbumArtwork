package com.example.serg.albumartwork.Presenter;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.serg.albumartwork.AlbumClicked;
import com.example.serg.albumartwork.LayoutManagerProvider;
import com.example.serg.albumartwork.Model.Album;
import com.example.serg.albumartwork.Model.Catalog;
import com.example.serg.albumartwork.R;
import com.example.serg.albumartwork.View.AlbumView;
import com.example.serg.albumartwork.View.IAlbumView;
import com.example.serg.albumartwork.View.ICatalogView;

import java.util.List;
import java.util.Observable;

public class CatalogPresenter implements ICatalogPresenter {

    private final ICatalogView catalogView;
    private final LayoutManagerProvider provider;
    private AlbumClicked albumClicked;

    public CatalogPresenter(ICatalogView catalogView,LayoutManagerProvider provider, AlbumClicked albumClicked) {
        this.catalogView= catalogView;
        this.provider = provider;
        this.albumClicked = albumClicked;
    }

    @Override
    public void updateCatalog(Catalog catalog){
        if(catalog.getAlbums() != null) {
            if (catalog.getAlbums().size() != 0) {
                catalogView.getNothingFound().setVisibility(View.INVISIBLE);
                RecyclerView.LayoutManager layoutManager = provider.provideLayoutManger();
                catalogView.getAlbumsRecyclerView().setLayoutManager(layoutManager);
                catalogView.getAlbumsRecyclerView().setHasFixedSize(true);
                catalogView.getAlbumsRecyclerView().setAdapter(
                        new AlbumsAdapter(catalog, albumClicked));
            } else {
                catalogView.getNothingFound().setVisibility(View.VISIBLE);
                catalogView.getAlbumsRecyclerView().setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        if (null != o && o instanceof Catalog){
            Catalog catalog = (Catalog) o;
            Log.d("CatPres", "Update!");
            hideProgress();
            updateCatalog(catalog);
        }
    }

    @Override
    public void showProgress() {
        catalogView.getAlbumsRecyclerView().setVisibility(View.INVISIBLE);
        catalogView.getProgressBar().setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        catalogView.getProgressBar().setVisibility(View.INVISIBLE);
        catalogView.getAlbumsRecyclerView().setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoInternetSnack() {
        Snackbar.make(catalogView.getMainLayout(),
                R.string.no_connection,
                Snackbar.LENGTH_SHORT)
                .show();
    }

    private static class AlbumsAdapter extends RecyclerView.Adapter<AlbumView> {

        private AlbumClicked albumClicked;
        private List<Album> albums;

        public AlbumsAdapter(Catalog catalog, AlbumClicked albumClicked) {
            this.albums = catalog.getAlbums();
            this.albumClicked= albumClicked;
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
            holder.itemView.setTag(R.integer.albumNum, position);
            IAlbumPresenter albumPresenter = new AlbumPresenter(holder);
            albumPresenter.updateAlbum(albums.get(position), albumClicked.showAlbumInfo());
            Log.d("bindView", "pos: " + position);
        }

        @Override
        public int getItemCount() {
            if(albums != null)
                return albums.size();
            else
                return 0;
        }
    }
}
