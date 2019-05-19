package com.example.aplikasimoviecatalouge.search;

import com.example.aplikasimoviecatalouge.BuildConfig;
import com.example.aplikasimoviecatalouge.api.ApiClient;
import com.example.aplikasimoviecatalouge.movie.ModelMovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMoviePresenter implements SearchContract.SearchContractPresenter {
    String query;
    String languge;
    SearchContract.SearchContractView searchContractView;

    public SearchMoviePresenter(String query, String languge, SearchContract.SearchContractView searchContractView) {
        this.query = query;
        this.languge = languge;
        this.searchContractView = searchContractView;
    }

    @Override
    public void index() {
        ApiClient apiClient = new ApiClient();
        apiClient.apiService.searchMove(BuildConfig.TMDB_API_KEY,languge,query).enqueue(new Callback<ModelMovieResponse>() {
            @Override
            public void onResponse(Call<ModelMovieResponse> call, Response<ModelMovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        searchContractView.listSearchMovie(response.body().getModelMovies());
                    }else {
                        searchContractView.msg("gagal");
                    }
                }else {
                    searchContractView.msg("gagal");
                }
            }

            @Override
            public void onFailure(Call<ModelMovieResponse> call, Throwable t) {
                searchContractView.msg("Koneksi Bermasalah");
            }
        });
    }
}
