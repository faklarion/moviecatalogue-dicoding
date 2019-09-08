package com.studio.faisal.moviecatalogue2.searchview;

import android.app.SearchManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.support.v7.widget.SearchView;
import com.studio.faisal.moviecatalogue2.movie.ApiClient;
import com.studio.faisal.moviecatalogue2.movie.ApiInterface;
import com.studio.faisal.moviecatalogue2.movie.Film;
import com.studio.faisal.moviecatalogue2.movie.ListFilmAdapter;
import com.studio.faisal.moviecatalogue2.movie.MovieResponse;
import com.studio.faisal.moviecatalogue2.R;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class carimovie extends AppCompatActivity {
    RecyclerView recyclerView;
    private final static String API_KEY = "9231e0b192b1abf0c0701eca28cc549a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carimovie);
        recyclerView = findViewById(R.id.rv_carimovie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getResources().getString(R.string.carimovie));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_cari, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);
                Call<MovieResponse> call = apiService.getSearchMovies(API_KEY, query);
                call.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse>call, Response<MovieResponse> response) {
                        List<Film> movies = response.body().getResults();
                        recyclerView.setAdapter(new ListFilmAdapter(getApplicationContext(), movies));
                    }

                    @Override
                    public void onFailure(Call<MovieResponse>call, Throwable t) {
                        // Log error here since request failed

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}
