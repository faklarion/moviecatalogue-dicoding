package com.studio.faisal.moviecatalogue2.tabfavorite;

import android.annotation.SuppressLint;
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
import com.studio.faisal.moviecatalogue2.favoritetv.DataTV;
import com.studio.faisal.moviecatalogue2.favoritetv.DatabaseHelper;
import com.studio.faisal.moviecatalogue2.favoritetv.FavTVAdapter;
import java.util.ArrayList;
import java.util.List;

public class TabTV extends Fragment {
    private FavTVAdapter adapter;
    private List<DataTV> movieList;
    public TabTV() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.fav_list_tv);
        movieList = new ArrayList<>();
        adapter = new FavTVAdapter(getActivity(), movieList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getAllFavorite();
        return view;
    }
    @SuppressLint("StaticFieldLeak")
    private void getAllFavorite(){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params){
                DatabaseHelper favDb = new DatabaseHelper(getActivity());
                movieList.clear();
                movieList.addAll(favDb.getAllFavorite());
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }
    @Override
    public void onResume(){
        super.onResume();
        if (movieList.isEmpty()){
            getAllFavorite();
        }else{
            getAllFavorite();
        }
    }
}
