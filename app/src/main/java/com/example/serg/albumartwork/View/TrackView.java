package com.example.serg.albumartwork.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TrackView extends RecyclerView.ViewHolder implements ITrackView {

    private TextView artistName;
    private TextView trackName;
    private TextView duration;

    public TrackView(View itemView, TextView artistName, TextView trackName, TextView duration) {
        super(itemView);
        this.artistName = artistName;
        this.trackName = trackName;
        this.duration = duration;
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
    public void setDuration(String duration) {
        this.duration.setText(duration);
    }
}
