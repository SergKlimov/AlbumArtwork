package com.example.serg.albumartwork.Presenter;

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
    //private final Activity activity;
    //private List<Album> albums;

    public CatalogPresenter(ICatalogView catalogView,/*, Activity activity, List<Album> albums*/LayoutManagerProvider provider, AlbumClicked albumClicked) {
        this.catalogView= catalogView;
        //this.activity = activity;
        //this.albums = albums;
        this.provider = provider;
        this.albumClicked = albumClicked;
    }

    @Override
    public void updateCatalog(Catalog catalog){
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.activity);
        RecyclerView.LayoutManager layoutManager = provider.provideLayoutManger();
        catalogView.getAlbumsRecyclerView().setLayoutManager(layoutManager);
        catalogView.getAlbumsRecyclerView().setHasFixedSize(true);
        catalogView.getAlbumsRecyclerView().setAdapter(
                new AlbumsAdapter(catalog, albumClicked));
    }

    @Override
    public void update(Observable observable, Object o) {
        if (null != o && o instanceof Catalog){
            Catalog catalog = (Catalog) o;
            Log.d("CatPres", "Update!");
            updateCatalog(catalog);
        }
    }

    private static class AlbumsAdapter extends RecyclerView.Adapter<AlbumView> {

        /*private AlbumClicked albumClicked;*/
        private AlbumClicked albumClicked;
        List<Album> albums;

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
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) holder;
            viewHolder.itemView.setTag(R.integer.albumNum, position);
            IAlbumPresenter albumPresenter = new AlbumPresenter((IAlbumView) viewHolder/*, albumClicked*/);
            albumPresenter.updateAlbum(albums.get(position), albumClicked.showAlbumInfo());
            Log.d("bindView", "pos: " + position);
        }

        @Override
        public int getItemCount() {
            return albums.size();
        }
    }
}
