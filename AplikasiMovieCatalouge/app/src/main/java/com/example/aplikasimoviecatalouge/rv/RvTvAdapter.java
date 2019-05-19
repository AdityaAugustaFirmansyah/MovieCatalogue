package com.example.aplikasimoviecatalouge.rv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplikasimoviecatalouge.R;
import com.example.aplikasimoviecatalouge.tvshow.ModelTvShow;

import java.util.List;

public class RvTvAdapter extends RecyclerView.Adapter<RvTvHolder> {
    private List<ModelTvShow> modelTvShows;
    private Context context;

    public void setModelTvShows(List<ModelTvShow> modelTvShows, Context context) {
        this.modelTvShows = modelTvShows;
        this.context = context;
    }

    @NonNull
    @Override
    public RvTvHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new RvTvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvTvHolder rvTvHolder, int i) {
        rvTvHolder.bindItem(modelTvShows.get(i), context);
    }

    @Override
    public int getItemCount() {
        Log.e("INIBERAPA", String.valueOf(modelTvShows.size()));
        return modelTvShows.size();
    }
}
