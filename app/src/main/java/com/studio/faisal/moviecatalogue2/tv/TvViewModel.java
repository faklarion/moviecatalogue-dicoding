package com.studio.faisal.moviecatalogue2.tv;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class TvViewModel extends ViewModel {
    private final static String API_KEY = "9231e0b192b1abf0c0701eca28cc549a";
    private MutableLiveData<List<Tv>> filmList;

    public LiveData<List<Tv>> getMovieDetails() {
        //if the list is null
        if (filmList == null) {
            filmList = new MutableLiveData<List<Tv>>();
            //we will load it asynchronously from server in this method
            loadFilm();
        }

        //finally we will return the list
        return filmList;
    }

    private void loadFilm(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TvResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                filmList.setValue(response.body().getResults());
            }
            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

    }
}
