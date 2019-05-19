package com.example.aplikasimoviecatalouge.movie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.aplikasimoviecatalouge.BuildConfig;
import com.example.aplikasimoviecatalouge.api.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter implements MovieContract.ModelContractPresenter {
    private MovieContract.ModelContractView modelContractView;
    private String language;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public MoviePresenter(MovieContract.ModelContractView modelContractView, String language, ProgressBar progressBar, RecyclerView recyclerView) {
        this.modelContractView = modelContractView;
        this.language = language;
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;
    }

    @Override
    public void index() {
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        final Call<ModelMovieResponse> modelMovieResponseCall = new ApiClient().apiService.results(BuildConfig.TMDB_API_KEY, language);
        modelMovieResponseCall.enqueue(new Callback<ModelMovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<ModelMovieResponse> call, @NonNull Response<ModelMovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        modelContractView.listMovie(response.body().getModelMovies());
                        Log.i("INFOBODY", String.valueOf(response.body().getModelMovies().size()));
                    } else {
                        modelContractView.showError("NULL");
                    }
                } else {
                    modelContractView.showError(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ModelMovieResponse> call, @NonNull Throwable t) {
                modelContractView.showError(t.getMessage());
            }
        });
    }

}
