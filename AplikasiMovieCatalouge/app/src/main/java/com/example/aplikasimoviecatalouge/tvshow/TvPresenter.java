package com.example.aplikasimoviecatalouge.tvshow;

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

public class TvPresenter implements TvContract.TvPresenterContract {

    private TvContract.TvViewContract tvViewContract;
    private String language;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    TvPresenter(TvContract.TvViewContract tvViewContract, String language, ProgressBar progressBar,RecyclerView recyclerView) {
        this.tvViewContract = tvViewContract;
        this.language = language;
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;
    }

    @Override
    public void index() {
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        ApiClient apiClient = new ApiClient();
        apiClient.apiService.listTv(BuildConfig.TMDB_API_KEY, language).enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(@NonNull Call<ModelResponse> call, @NonNull Response<ModelResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        tvViewContract.listTv(response.body());
                        Log.e("INIERROR3", response.message());
                    } else {
                        tvViewContract.msg("NULL");
                        Log.e("INIERROR2", response.message());
                    }
                } else {
                    tvViewContract.msg("SERVER BERMASALAH");
                    Log.e("INIERROR4", response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ModelResponse> call, @NonNull Throwable t) {
                tvViewContract.msg("KONEKSI GAGAL");
                Log.e("INIERROR", t.getMessage());
            }
        });
    }
}
