package com.example.serg.albumartwork.View;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TrackView extends RecyclerView.ViewHolder implements ITrackView {

    private TextView artistName;
    private TextView trackName;
    //private AppCompatImageView cover;
    private TextView durationMillis;

    public TrackView(View itemView, TextView artistName, TextView trackName, /*AppCompatImageView cover,*/ TextView durationMillis) {
        super(itemView);
        this.artistName = artistName;
        this.trackName = trackName;
        //this.cover = cover;
        this.durationMillis = durationMillis;
    }

    @Override
    public void setArtistName(String artistName) {
        this.artistName.setText(artistName);
    }

    @Override
    public void setTrackName(String trackName) {
        this.trackName.setText(trackName);
    }

    @Override
    public void setDurationMillis(int durationMillis) {
        this.durationMillis.setText(String.valueOf(durationMillis));
    }
}
