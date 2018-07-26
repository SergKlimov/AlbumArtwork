package com.example.serg.albumartwork.View;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.serg.albumartwork.Model.Album;

public interface ICatalogView {
    public RecyclerView getAlbumsRecyclerView();
    public ProgressBar getProgressBar();
    public AppCompatTextView getNothingFound();
    public CoordinatorLayout getMainLayout();
}
