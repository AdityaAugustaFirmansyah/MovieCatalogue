package com.example.aplikasimoviecatalouge.rv;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aplikasimoviecatalouge.R;
import com.example.aplikasimoviecatalouge.detail.DetailFilmActivity;
import com.example.aplikasimoviecatalouge.movie.ModelMovie;
import com.example.aplikasimoviecatalouge.sql.MovieEntity;

import java.util.List;

class RvViewHolderMovie extends RecyclerView.ViewHolder {
    private TextView titleMovie;
    private ImageView posterMovie;
    private RelativeLayout relativeLayoutList;
    RvViewHolderMovie(@NonNull View itemView) {
        super(itemView);
        titleMovie = itemView.findViewById(R.id.title_movie);
        posterMovie = itemView.findViewById(R.id.poster_movie);
        relativeLayoutList = itemView.findViewById(R.id.rl_item);
    }

    void bindItem(final List<ModelMovie> modelMovies, final Context context, final int i){
        Glide.with(itemView).load("https://image.tmdb.org/t/p/w185" +modelMovies.get(i).getPostterMovie()).into(posterMovie);
        titleMovie.setText(modelMovies.get(i).getTitle());
        relativeLayoutList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ModelMovie modelMovie = new ModelMovie();
                    modelMovie.setIdMovie(modelMovies.get(i).getIdMovie());
                    Log.d("IDNYA",modelMovies.get(i).getIdMovie());
                    modelMovie.setPoster_path(modelMovies.get(i).getPostterMovie());
                    modelMovie.setName(modelMovies.get(i).getTitle());
                    Intent intent  = new Intent(context, DetailFilmActivity.class);
                    String desc = modelMovies.get(i).getOverviewMovie();
                    intent.putExtra(DetailFilmActivity.DESC,desc);
                    intent.putExtra(DetailFilmActivity.MODEL,modelMovie);
                context.startActivity(intent);

            };
        });
    }
}