package com.example.aplikasimoviecatalouge.search;

import android.support.annotation.NonNull;

import com.example.aplikasimoviecatalouge.BuildConfig;
import com.example.aplikasimoviecatalouge.api.ApiClient;
import com.example.aplikasimoviecatalouge.tvshow.ModelResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTvPresenter implements SearchContract.SearchContractPresenter {
    private String query;
    private String language;
    public SearchTvPresenter(String query, String language, SearchContract.SearchContractView searchContractView) {
        this.query = query;
        this.language = language;
        this.searchContractView = searchContractView;
    }
    private SearchContract.SearchContractView searchContractView;
    @Override
    public void index() {
        ApiClient apiClient = new ApiClient();
        apiClient.apiService.searchTv(BuildConfig.TMDB_API_KEY,language,query).enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(@NonNull Call<ModelResponse> call, @NonNull Response<ModelResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        searchContractView.listSearch(response.body().getListTv());
                    }
                }else {
                    searchContractView.msg("gagal");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ModelResponse> call, @NonNull Throwable t) {
                searchContractView.msg("kesalahan koneksi");
            }
        });
    }
}
