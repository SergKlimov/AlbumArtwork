package com.example.serg.albumartwork;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

import com.example.serg.albumartwork.Dagger.Component.CatalogPresComponent;
import com.example.serg.albumartwork.Dagger.Component.DaggerCatalogPresComponent;
import com.example.serg.albumartwork.Dagger.Module.CatalogPresModule;
import com.example.serg.albumartwork.Dagger.Module.GlideRequests;
import com.example.serg.albumartwork.Model.Catalog;
import com.example.serg.albumartwork.Presenter.CatalogPresenter;
import com.example.serg.albumartwork.Presenter.ICatalogPresenter;
import com.example.serg.albumartwork.View.CatalogView;
import com.example.serg.albumartwork.View.ICatalogView;
import com.example.serg.albumartwork.iTunesAPI.iTunesAPIService;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements LayoutManagerProvider {

    private ICatalogView catalogView;
    private CatalogPresComponent catalogPresComponent;
    @Inject ICatalogPresenter catalogPresenter;
    @Inject Catalog catalog;
    @Inject GlideRequests glideRequests;
    //private AppCompatImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        catalogView = new CatalogView((RecyclerView)findViewById(R.id.albums_recycler));
        catalog = ArtworkApplication.getComponent().getCatalog();
        glideRequests = ArtworkApplication.getComponent().getGldie();
        catalogPresComponent = DaggerCatalogPresComponent.builder()
                .catalogPresModule(new CatalogPresModule(catalogView, this))
                .build();
        catalogPresenter = catalogPresComponent.getCatalogPresenter();
        catalogPresenter.updateCatalog(catalog);
    }

    @Override
    protected void onResume() {
        super.onResume();
        catalog.addObserver(this.catalogPresenter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        catalog.deleteObservers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        /*glideRequests.load("https://is4-ssl.mzstatic.com/image/thumb/Music/v4/03/4d/45/034d451a-0098-5b51-37c4-f301471d614e/source/100x100bb.jpg").
                into(img);*/
        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            Intent i = new Intent(this, iTunesAPIService.class);
            Bundle bundle = new Bundle();
            bundle.putString(AppResources.SEARCH_QUERY, query);
            i.putExtra(AppResources.INTENT_BUNDLE, bundle);
            startService(i);
            Log.d("Deb", "intent started!");
        }
    }

    @Override
    public RecyclerView.LayoutManager provideLayoutManger() {
        return new LinearLayoutManager(this);
    }
}
