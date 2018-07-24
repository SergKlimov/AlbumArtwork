package com.example.serg.albumartwork.Utils;

import android.util.JsonReader;
import android.util.Log;

import com.example.serg.albumartwork.Model.Album;
import com.example.serg.albumartwork.Model.Catalog;

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
                            new ArrayList<String>(album.getInt("trackCount")),
                            album.getInt("trackCount")
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

    public static void printAlbum(Album album){
        Log.d("Print:", album.getName());
        Log.d("Print:", album.getCover());
        Log.d("Print:", album.getReleaseDate());
        Log.d("Print:", album.getGenre());
        Log.d("Print:", album.getArtist());
        Log.d("Print:", ""+album.getTrackList().size());
    }
}
