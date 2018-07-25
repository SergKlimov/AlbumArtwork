package com.example.serg.albumartwork.iTunesAPI;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.serg.albumartwork.AlbumInfoActivity;
import com.example.serg.albumartwork.AppResources;
import com.example.serg.albumartwork.ArtworkApplication;
import com.example.serg.albumartwork.Model.Catalog;
import com.example.serg.albumartwork.Utils.JsonParser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;

public class iTunesAPIService extends IntentService {

    @Inject Catalog catalog;

    private String ApiUrl = "https://itunes.apple.com/search?";
    private String lookupApiUrl = "https://itunes.apple.com/lookup?";

    public iTunesAPIService() {
        super("iTunesAPIService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("Intent", "onHandleIntent tut!");
        catalog = ArtworkApplication.getComponent().getCatalog();
        RequestQueue queue = Volley.newRequestQueue(this);
        if (intent != null){
            if (intent.getBundleExtra(AppResources.INTENT_BUNDLE) != null) {
                Bundle bundle = intent.getBundleExtra(AppResources.INTENT_BUNDLE);
                if(bundle.getString(AppResources.SERVICE_CMD).equals(AppResources.SERVICE_SEARCH_ALBUM)){
                    String query = bundle.getString(AppResources.SEARCH_QUERY);

                    String encodedQuery = null;
                    try {
                        encodedQuery = URLEncoder.encode(query, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String searchQuery = ApiUrl + "term=" + encodedQuery + "&entity=album";
                    Log.d("Intent", searchQuery);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET,
                            searchQuery, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Intent", response);
                            catalog.setCatalog(JsonParser.parse(response));
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Intent", "error in req!");
                        }
                    });
                    queue.add(stringRequest);
                    queue.start();
                }else {
                    final int albumNum = bundle.getInt(AppResources.ALBUM_NUMBER);
                    String lookup = lookupApiUrl + "id=" + catalog.getAlbums().get(albumNum).getiTunesId() + "&entity=song&explicit=Yes";
                    StringRequest req = new StringRequest(Request.Method.GET,
                            lookup, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Intent album", response);
                            JsonParser.loadTracks(response, catalog, albumNum);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Intent", "error in reqqqqqq!");
                        }
                    });

                    queue.add(req);
                }
                queue.start();
            }
        }
    }


}
