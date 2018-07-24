package com.example.serg.albumartwork.View;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class AlbumView extends RecyclerView.ViewHolder implements IAlbumView {

    private TextView albumName;
    private TextView albumYear;
    private TextView tracksCount;
    private AppCompatImageView cover;

    public AlbumView(View itemView, TextView albumName, TextView albumYear, TextView tracksCount, AppCompatImageView cover) {
        super(itemView);
        this.albumName = albumName;
        this.albumYear = albumYear;
        this.tracksCount = tracksCount;
        this.cover = cover;
    }

    @Override
    public void setName(String albumName) {
        this.albumName.setText(albumName);
    }

    @Override
    public void setYear(String year) {
        this.albumYear.setText(year);
    }

    @Override
    public void setTracksCount(String tracksCount) {
        this.tracksCount.setText(tracksCount);
    }

    @Override
    public IAlbumView getInstance() {
        return this;
    }

    public TextView getAlbumName() {
        return albumName;
    }

    public TextView getAlbumYear() {
        return albumYear;
    }

    public TextView getTracksCount() {
        return tracksCount;
    }

    @Override
    public AppCompatImageView getCover() {
        return cover;
    }
}
