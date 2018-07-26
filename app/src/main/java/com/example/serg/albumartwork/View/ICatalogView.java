package com.example.serg.albumartwork.View;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

public interface ICatalogView {
    RecyclerView getAlbumsRecyclerView();
    ProgressBar getProgressBar();
    AppCompatTextView getNothingFound();
    CoordinatorLayout getMainLayout();
}
