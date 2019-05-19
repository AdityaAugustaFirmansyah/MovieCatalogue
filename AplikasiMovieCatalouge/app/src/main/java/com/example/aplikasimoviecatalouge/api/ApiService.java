package com.example.aplikasimoviecatalouge.api;

import com.example.aplikasimoviecatalouge.movie.ModelMovieResponse;
import com.example.aplikasimoviecatalouge.tvshow.ModelResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("3/discover/movie?api_key=&language=")
    Call<ModelMovieResponse>results(@Query("api_key")String api_key,@Query("language")String language);

    @GET("3/discover/tv?api_key=&language=")
    Call<ModelResponse>listTv(@Query("api_key")String api_key, @Query("language")String language);

    @GET("3/search/movie?api_key=&language=&query=")
    Call<ModelMovieResponse>searchMove(@Query("api_key")String api_key, @Query("language")String language,@Query("query")String query);

    @GET("3/search/tv?api_key=&language=&query=")
    Call<ModelResponse>searchTv(@Query("api_key")String api_key, @Query("language")String language,@Query("query")String query);
}
