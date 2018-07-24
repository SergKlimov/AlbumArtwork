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
import com.example.serg.albumartwork.AppResources;
import com.example.serg.albumartwork.ArtworkApplication;
import com.example.serg.albumartwork.Model.Album;
import com.example.serg.albumartwork.Model.Catalog;
import com.example.serg.albumartwork.Utils.JsonParser;

import java.util.List;

import javax.inject.Inject;

public class iTunesAPIService extends IntentService {

    @Inject Catalog catalog;

    private String ApiUrl = "https://itunes.apple.com/search?";

    public iTunesAPIService() {
        super("iTunesAPIService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("Intent", "onHandleIntent tut!");
        catalog = ArtworkApplication.getComponent().getCatalog();
        if (intent != null){
            if (intent.getBundleExtra(AppResources.INTENT_BUNDLE) != null) {
                Bundle bundle = intent.getBundleExtra(AppResources.INTENT_BUNDLE);
                String query = bundle.getString(AppResources.SEARCH_QUERY);
                RequestQueue queue = Volley.newRequestQueue(this);
                String searchQuery = ApiUrl + "term=" + query + "&entity=album";
                Log.d("Intent", searchQuery);
                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        searchQuery, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(iTunesAPIService.this, response.substring(0, 30), Toast.LENGTH_SHORT).show();
                        Log.d("Intent", response);
                        catalog.setCatalog(JsonParser.parse(response));
                        JsonParser.printAlbum(catalog.getAlbums().get(0));
                        JsonParser.printAlbum(catalog.getAlbums().get(catalog.getAlbums().size()-1));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Intent", "error in req!");
                    }
                });
                queue.add(stringRequest);
                queue.start();
            }
        }
    }


}
