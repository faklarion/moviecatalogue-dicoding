package com.studio.faisal.moviecatalogue2.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.squareup.picasso.Picasso;
import com.studio.faisal.moviecatalogue2.R;
import com.studio.faisal.moviecatalogue2.favoritemovie.DataMovie;
import com.studio.faisal.moviecatalogue2.favoritemovie.MovieHelper;

import java.util.ArrayList;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private ArrayList<DataMovie> movieList;
    private Context mContext;
    private int mAppWidgetId;
    private MovieHelper helper;
    public StackRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {

    }
    @Override
    public void onDataSetChanged() {
        helper = new MovieHelper(mContext);
        helper.open();
        movieList = new ArrayList<>();
        movieList.addAll(helper.getData());
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return movieList.size();
    }
    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        Bitmap bmp = null;
        try {
            bmp =  Picasso.get().load("http://image.tmdb.org/t/p/w300/"+movieList.get(position).getPoster()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("log", movieList.get(position).getJudul());
        rv.setImageViewBitmap(R.id.imageView, bmp);
        rv.setTextViewText(R.id.tv_title, movieList.get(position).getJudul());

        Bundle bundle = new Bundle();
        bundle.putInt(FavoriteWidget.EXTRA_ITEM, position);
        Intent fillIntent = new Intent();
        fillIntent.putExtras(bundle);
        rv.setOnClickFillInIntent(R.id.imageView, fillIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
