package com.example.aplikasimoviecatalouge.api;

import com.example.aplikasimoviecatalouge.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public ApiService apiService = retrofit.create(ApiService.class);
}