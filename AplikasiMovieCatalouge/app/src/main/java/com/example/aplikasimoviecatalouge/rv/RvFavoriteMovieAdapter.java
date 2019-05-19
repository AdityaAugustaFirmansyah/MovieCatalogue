package com.example.aplikasimoviecatalouge.rv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplikasimoviecatalouge.R;
import com.example.aplikasimoviecatalouge.sql.MovieEntity;

import java.util.ArrayList;

public class RvFavoriteMovieAdapter extends RecyclerView.Adapter<RvFavoriteMovieHolder> {
    private ArrayList<MovieEntity> movieEntities;
    private Context context;
    public void setMovieEntities(ArrayList<MovieEntity> movieEntities) {
        if (movieEntities.size()>0){
            this.movieEntities = movieEntities;
        }else {
            this.movieEntities.addAll(movieEntities);
            notifyDataSetChanged();
        }
    }

    public RvFavoriteMovieAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RvFavoriteMovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        return new RvFavoriteMovieHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RvFavoriteMovieHolder rvFavoriteMovieHolder, int i) {
        rvFavoriteMovieHolder.bindItem(movieEntities.get(i),context);
    }

    @Override
    public int getItemCount() {

        return movieEntities.size();
    }
}
