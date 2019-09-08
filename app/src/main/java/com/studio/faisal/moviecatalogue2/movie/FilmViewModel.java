package com.studio.faisal.moviecatalogue2.movie;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class FilmViewModel extends ViewModel {
    private final static String API_KEY = "9231e0b192b1abf0c0701eca28cc549a";
    private MutableLiveData<List<Film>> filmList;

    public LiveData<List<Film>> getMovieDetails() {
        //if the list is null
        if (filmList == null) {
            filmList = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            loadFilm();
        }

        //finally we will return the list
        return filmList;
    }

    private void loadFilm(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                filmList.setValue(response.body().getResults());
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

    }
}
