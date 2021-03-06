package com.example.serg.albumartwork.Utils;

import android.util.Log;

import com.example.serg.albumartwork.Model.Album;
import com.example.serg.albumartwork.Model.Catalog;
import com.example.serg.albumartwork.Model.Track;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
                    String timeStamp = album.getString("releaseDate");
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
                    Date date = new Date();
                    try {
                        date = format.parse(timeStamp);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    albums.add(new Album(album.getString("collectionName"),
                            album.getString("artworkUrl100"),
                            date,
                            album.getString("primaryGenreName"),
                            album.getString("artistName"),
                            new ArrayList<Track>(album.getInt("trackCount")),
                            album.getInt("trackCount"),
                            album.getInt("collectionId")
                    ));
                    Log.d("Parser", "tracks: "+album.getInt("trackCount"));
                }
                Collections.sort(albums, new Comparator<Album>() {
                    @Override
                    public int compare(Album album1, Album album2) {
                        String left = album1.getName();
                        String right = album2.getName();
                        return left.compareTo(right);
                    }
                });
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
                for (int i = 1; i < response.getInt("resultCount"); i++){
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
}
