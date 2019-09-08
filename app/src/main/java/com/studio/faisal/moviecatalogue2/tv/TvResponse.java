package com.studio.faisal.moviecatalogue2.tv;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvResponse {
    @SerializedName("results")
    public List<Tv> results;

    public List<Tv> getResults() {
        return results;
    }

}
