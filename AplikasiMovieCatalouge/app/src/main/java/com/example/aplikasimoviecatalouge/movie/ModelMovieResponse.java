package com.example.aplikasimoviecatalouge.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelMovieResponse {
    @SerializedName("results")
    @Expose
    private
    List<ModelMovie> modelMovies;
    public List<ModelMovie> getModelMovies() {
        return modelMovies;
    }
}
