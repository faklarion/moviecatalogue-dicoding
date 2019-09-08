package com.studio.faisal.moviecatalogue2.movie;

import java.util.List;
import com.google.gson.annotations.SerializedName;
public class MovieResponse {

    @SerializedName("results")
    public List<Film> results;

    public List<Film> getResults() {
        return results;
    }

}
