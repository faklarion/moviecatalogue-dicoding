package com.studio.faisal.moviecatalogue2.tabfavorite;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.faisal.moviecatalogue2.R;
import com.studio.faisal.moviecatalogue2.favoritemovie.FavMovieAdapter;


import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.CONTENT_URI;

public class TabMovie extends Fragment {
    private FavMovieAdapter adapter;
    private Cursor movieList;
    public TabMovie() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.fav_list_movie);
        adapter = new FavMovieAdapter(getActivity(), movieList);
        adapter.setListMovie(movieList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
    @SuppressLint("StaticFieldLeak")
    public class loadData extends AsyncTask<Void, Void, Cursor> {
        @Override
        public void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        public Cursor doInBackground(Void... voids) {
            return getActivity().getApplicationContext().getContentResolver().query(CONTENT_URI,null,null,null,null);
        }

        @Override
        public void onPostExecute(Cursor movie) {
            super.onPostExecute(movie);

            movieList = movie;
            adapter.setListMovie(movieList);
            adapter.notifyDataSetChanged();

            if (movie.getCount() == 0){

            }
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        new loadData().execute();
    }
}