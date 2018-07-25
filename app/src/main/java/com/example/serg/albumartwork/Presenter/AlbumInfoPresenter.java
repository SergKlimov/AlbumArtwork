package com.example.serg.albumartwork.Presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.serg.albumartwork.ArtworkApplication;
import com.example.serg.albumartwork.Dagger.Module.GlideRequests;
import com.example.serg.albumartwork.LayoutManagerProvider;
import com.example.serg.albumartwork.Model.Album;
import com.example.serg.albumartwork.R;
import com.example.serg.albumartwork.View.IAlbumInfoView;
import com.example.serg.albumartwork.View.TrackView;

import java.util.Observable;

import javax.inject.Inject;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class AlbumInfoPresenter implements IAlbumInfoPresenter {

    private final IAlbumInfoView albumInfoView;
    private final LayoutManagerProvider provider;
    @Inject GlideRequests glideRequests;

    public AlbumInfoPresenter(IAlbumInfoView albumInfoView, LayoutManagerProvider provider) {
        this.albumInfoView = albumInfoView;
        this.provider = provider;
        glideRequests = ArtworkApplication.getComponent().getGldie();
    }

    @Override
    public void updateAlbumInfo(Album album){

        albumInfoView.getAlbumName().setText(album.getName());
        albumInfoView.getAlbumArtist().setText("Album by "+album.getArtist());
        //albumInfoView.getTracksCount().setText("tracks: " + album.getTrackList().size());
        albumInfoView.getAlbumGenre().setText(album.getGenre());
        //albumInfoView.getProgressBar().setVisibility(View.INVISIBLE);
        //albumInfoView.getAlbumReleaseDate().setText(album.getReleaseDate().toString());
        glideRequests.load(album.getCover())
                .transform(new RoundedCornersTransformation(5,0))
                .placeholder(R.drawable.album)
                .fitCenter()
                .into(albumInfoView.getCover());
        RecyclerView.LayoutManager layoutManager = provider.provideLayoutManger();
        albumInfoView.getTracksRecyclerView().setLayoutManager(layoutManager);
        albumInfoView.getTracksRecyclerView().setHasFixedSize(true);
        albumInfoView.getTracksRecyclerView().setAdapter(
                new TracksAdapter(album)
        );
        albumInfoView.getProgressBar().setVisibility(View.INVISIBLE);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (null != o && o instanceof Album){
            Album album = (Album) o;
            Log.d("AlbumInfoPresenter", "Got album update!");
            Log.d("AlbumInfoPresenter", album.getName());
            Log.d("AlbumInfoPresenter", album.getArtist());
            Log.d("AlbumInfoPresenter", album.getTracksCount()+"");
            Log.d("AlbumInfoPresenter", album.getGenre());
            Log.d("AlbumInfoPresenter", "Got album update!");
            updateAlbumInfo(album);
        }
    }

    private static class TracksAdapter extends RecyclerView.Adapter<TrackView>{

        Album album;

        public TracksAdapter(Album album) {
            this.album = album;
        }

        @NonNull
        @Override
        public TrackView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = null;
            TrackView trackView = null;
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.track_info, parent, false);
            trackView = new TrackView(view,
                    (TextView)view.findViewById(R.id.track_artist),
                    (TextView)view.findViewById(R.id.track_name),
                    (TextView)view.findViewById(R.id.track_duration));
            return trackView;
        }

        @Override
        public void onBindViewHolder(@NonNull TrackView holder, int position) {
            ITrackPresenter trackPresenter = new TrackPresenter(holder);
            trackPresenter.updateTrack(album.getTrackList().get(position));
        }

        @Override
        public int getItemCount() {
            return album.getTrackList().size();
        }
    }
}
