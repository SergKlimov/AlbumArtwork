package com.example.serg.albumartwork;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
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

public class MainActivity extends AppCompatActivity implements LayoutManagerProvider, AlbumClicked {

    private ICatalogView catalogView;
    private CatalogPresComponent catalogPresComponent;
    @Inject ICatalogPresenter catalogPresenter;
    @Inject Catalog catalog;
    @Inject GlideRequests glideRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icons8_itunes);
        catalogView = new CatalogView((RecyclerView)findViewById(R.id.albums_recycler),
                (ProgressBar)findViewById(R.id.catalog_progress),
                (AppCompatTextView)findViewById(R.id.not_found),
                (CoordinatorLayout)findViewById(R.id.main_layout));
        catalog = ArtworkApplication.getComponent().getCatalog();
        glideRequests = ArtworkApplication.getComponent().getGldie();
        catalogPresComponent = DaggerCatalogPresComponent.builder()
                .catalogPresModule(new CatalogPresModule(catalogView, this, this))
                .build();
        catalogPresenter = catalogPresComponent.getCatalogPresenter();

    }

    @Override
    protected void onResume() {
        Log.d("MainActivity", "onResume");
        super.onResume();
        catalog.addObserver(this.catalogPresenter);
        catalogPresenter.updateCatalog(catalog);
    }

    @Override
    protected void onPause() {
        Log.d("MainActivity", "onPause");
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
        if (Intent.ACTION_SEARCH.equals(intent.getAction()) && ArtworkApplication.getComponent().getConnectionStatus()){
            catalogPresenter.showProgress();
            String query = intent.getStringExtra(SearchManager.QUERY);
            Intent i = new Intent(this, iTunesAPIService.class);
            Bundle bundle = new Bundle();
            bundle.putString(AppResources.SEARCH_QUERY, query);
            bundle.putString(AppResources.SERVICE_CMD, AppResources.SERVICE_SEARCH_ALBUM);
            i.putExtra(AppResources.INTENT_BUNDLE, bundle);
            startService(i);
            Log.d("Deb", "intent started!");
        } else if (!ArtworkApplication.getComponent().getConnectionStatus()){
            catalogPresenter.showNoInternetSnack();
        }
    }

    @Override
    public RecyclerView.LayoutManager provideLayoutManger() {
        return new LinearLayoutManager(this);
    }

    @Override
    public View.OnClickListener showAlbumInfo() {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ArtworkApplication.getComponent().getConnectionStatus()){
                    int albumNum = (int) view.getTag(R.integer.albumNum);
                    Intent i = new Intent(getApplicationContext(), iTunesAPIService.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(AppResources.ALBUM_NUMBER, albumNum);
                    bundle.putString(AppResources.SERVICE_CMD, AppResources.SERVICE_GET_TRACKS);
                    i.putExtra(AppResources.INTENT_BUNDLE, bundle);
                    startService(i);

                    Intent showAlbumInfo = new Intent(MainActivity.this, AlbumInfoActivity.class);
                    Bundle b = new Bundle();
                    b.putInt(AppResources.ALBUM_NUMBER, albumNum);
                    showAlbumInfo.putExtra(AppResources.ALBUM_NUMBER_BUNDLE, b);
                    startActivity(showAlbumInfo);
                }else {
                    catalogPresenter.showNoInternetSnack();
                }
            }
        };
        return clickListener;
    }
}
