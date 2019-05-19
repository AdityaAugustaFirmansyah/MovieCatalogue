package com.example.aplikasimoviecatalouge.rv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplikasimoviecatalouge.R;
import com.example.aplikasimoviecatalouge.movie.ModelMovie;

import java.util.List;

public class RvAdapterMovie extends RecyclerView.Adapter<RvViewHolderMovie> {
    private Context context;
    private List<ModelMovie> results;

    public RvAdapterMovie(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RvViewHolderMovie onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new RvViewHolderMovie(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvViewHolderMovie rvViewHolderMovie, int i) {
        rvViewHolderMovie.bindItem(results, context, i);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<ModelMovie> results) {
        this.results = results;
    }
}
