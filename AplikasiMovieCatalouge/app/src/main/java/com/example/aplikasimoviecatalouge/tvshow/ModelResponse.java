package com.example.aplikasimoviecatalouge.tvshow;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelResponse {
    @SerializedName("results")
    private
    List<ModelTvShow> listTv;

    public List<ModelTvShow> getListTv() {
        return listTv;
    }

    public void setListTv(List<ModelTvShow> listTv) {
        this.listTv = listTv;
    }
}
