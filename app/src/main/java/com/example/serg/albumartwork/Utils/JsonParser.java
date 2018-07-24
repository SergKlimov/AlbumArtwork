package com.example.serg.albumartwork.Utils;

import android.util.JsonReader;
import android.util.Log;

import com.example.serg.albumartwork.Model.Album;
import com.example.serg.albumartwork.Model.Catalog;
import com.example.serg.albumartwork.Model.Track;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static Catalog parse(String responseResult) {
        List<Album> albums = new ArrayList<>();
        Catalog catalog = new Catalog();
        if (!responseResult.equals(null)) {
            try {
                JSONObject response = new JSONObject(responseResult);
                int albumsCount = response.getInt("resultCount");
                JSONArray jsonAlbums = response.getJSONArray("results");
                for (int i = 0; i < albumsCount; i++){
                    JSONObject album = jsonAlbums.getJSONObject(i);
                    albums.add(new Album(album.getString("collectionName"),
                            album.getString("artworkUrl100"),
                            album.getString("releaseDate"),
                            album.getString("primaryGenreName"),
                            album.getString("artistName"),
                            new ArrayList<Track>(album.getInt("trackCount")),
                            album.getInt("trackCount"),
                            album.getInt("collectionId")
                    ));
                    Log.d("Parser", "tracks: "+album.getInt("trackCount"));
                }
                catalog = new Catalog(albums);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return catalog;
    }

    public static void loadTracks(String responseResult, Catalog catalog, int albumNum){
        Album album = catalog.getAlbums().get(albumNum);
        List<Track> tracks = new ArrayList<>(album.getTracksCount());
        if (!responseResult.equals(null)) {
            try {
                JSONObject response = new JSONObject(responseResult);
                JSONArray jsonTracks = response.getJSONArray("results");
                for (int i = 1; i < album.getTracksCount() + 1; i++){
                    JSONObject jsonTrack = jsonTracks.getJSONObject(i);
                    tracks.add(new Track(jsonTrack.getString("artistName"),
                            jsonTrack.getString("trackName"),
                            jsonTrack.getString("artworkUrl100"),
                            jsonTrack.getInt("trackTimeMillis")));
                }
                catalog.setAlbumTracks(albumNum, tracks);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printAlbumTracks(Album album){
        for (int i = 0; i < album.getTrackList().size(); i++){
            Log.d("Print track:", "-----------");
            Log.d("Print track:", album.getTrackList().get(i).getTrackName());
            Log.d("Print track:", "-----------");
        }
    }

    public static void printAlbum(Album album){
        Log.d("Print:", album.getName());
        Log.d("Print:", album.getCover());
        Log.d("Print:", album.getReleaseDate());
        Log.d("Print:", album.getGenre());
        Log.d("Print:", album.getArtist());
        Log.d("Print:", ""+album.getTrackList().size());
    }
}
