package com.studio.faisal.moviecatalogue2.movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("discover/movie")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("discover/movie")
    Call<MovieResponse>getUpcomingMovies(@Query("api_key") String apiKey, @Query("primary_release_date.gte") String gte,
    @Query("primary_release_date.lte") String lte, @Query("page") int page
    );

    @GET("search/movie")
    Call<MovieResponse> getSearchMovies(@Query("api_key") String apiKey, @Query("query") String query);
}
