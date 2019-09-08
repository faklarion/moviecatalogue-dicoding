package com.studio.faisal.moviecatalogue2.searchview;

import android.app.SearchManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import com.studio.faisal.moviecatalogue2.R;
import com.studio.faisal.moviecatalogue2.tv.ApiClient;
import com.studio.faisal.moviecatalogue2.tv.ApiInterface;
import com.studio.faisal.moviecatalogue2.tv.ListTvAdapter;
import com.studio.faisal.moviecatalogue2.tv.Tv;
import com.studio.faisal.moviecatalogue2.tv.TvResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class caritv extends AppCompatActivity {
    RecyclerView recyclerView;
    private final static String API_KEY = "9231e0b192b1abf0c0701eca28cc549a";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cairtv);
        recyclerView = findViewById(R.id.rv_caritv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getResources().getString(R.string.caritv));
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_caritv, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.stv).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);
                Call<TvResponse> call = apiService.getSearchTv(API_KEY, query);
                call.enqueue(new Callback<TvResponse>() {
                    @Override
                    public void onResponse(Call<TvResponse>call, Response<TvResponse> response) {
                        List<Tv> movies = response.body().getResults();
                        recyclerView.setAdapter(new ListTvAdapter(getApplicationContext(), movies));
                    }

                    @Override
                    public void onFailure(Call<TvResponse>call, Throwable t) {
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
