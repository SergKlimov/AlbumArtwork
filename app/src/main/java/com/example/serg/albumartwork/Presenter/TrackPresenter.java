package com.example.serg.albumartwork.Presenter;

import com.example.serg.albumartwork.Model.Track;
import com.example.serg.albumartwork.Utils.MillisecParser;
import com.example.serg.albumartwork.View.ITrackView;

public class TrackPresenter implements ITrackPresenter {

    private ITrackView trackView;

    public TrackPresenter(ITrackView trackView) {
        this.trackView = trackView;
    }

    @Override
    public void updateTrack(Track track) {
        if (!track.equals(null)){
            trackView.setArtistName(track.getArtistName());
            trackView.setTrackName(track.getTrackName());
            trackView.setDuration(MillisecParser.getString(track.getDurationMillis()));
        }
    }
}
