package com.studio.faisal.moviecatalogue2.tv;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("discover/tv")
    Call<TvResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("search/tv")
    Call<TvResponse> getSearchTv(@Query("api_key") String apiKey, @Query("query") String query);
}
