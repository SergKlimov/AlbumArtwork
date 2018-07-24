package com.example.serg.albumartwork.Presenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.serg.albumartwork.AlbumClicked;
import com.example.serg.albumartwork.ArtworkApplication;
import com.example.serg.albumartwork.Dagger.Module.GlideRequests;
import com.example.serg.albumartwork.Model.Album;
import com.example.serg.albumartwork.R;
import com.example.serg.albumartwork.View.IAlbumView;

import javax.inject.Inject;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class AlbumPresenter implements IAlbumPresenter {

    private IAlbumView albumView;
    @Inject GlideRequests glideRequests;

    public AlbumPresenter(IAlbumView albumView) {
        this.albumView = albumView;
        glideRequests = ArtworkApplication.getComponent().getGldie();
    }

    @Override
    public void updateAlbum(Album album, View.OnClickListener clickListener) {
        if(!album.equals(null)) {
            glideRequests.load(album.getCover())
                    .transform(new RoundedCornersTransformation(5,0))
                    .placeholder(R.drawable.album)
                    .into(albumView.getCover());
            albumView.setName(album.getName());
            albumView.setYear(album.getReleaseDate());
            albumView.setTracksCount(String.valueOf(album.getTracksCount()));
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) albumView;
            viewHolder.itemView.setOnClickListener(clickListener);
        }
    }
}
