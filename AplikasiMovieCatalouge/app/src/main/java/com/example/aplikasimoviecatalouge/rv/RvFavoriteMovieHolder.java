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

class RvFavoriteMovieHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private ImageView imageView;
    private RelativeLayout relativeLayout;
    RvFavoriteMovieHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.title_movie);
        imageView = itemView.findViewById(R.id.poster_movie);
        relativeLayout = itemView.findViewById(R.id.rl_item);
    }

    void bindItem(final MovieEntity movieEntity, final Context context){
        textView.setText(movieEntity.getName());
        Glide.with(itemView).load("https://image.tmdb.org/t/p/w185/" + movieEntity.getPoster_path()).into(imageView);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelMovie modelMovie = new ModelMovie();
                modelMovie.setIdMovie(String.valueOf(movieEntity.getId()));
                Log.d("IDNYA",String.valueOf(movieEntity.getId()));
                modelMovie.setPoster_path(movieEntity.getPoster_path());
                modelMovie.setName(movieEntity.getName());
                Intent intent = new Intent(context, DetailFilmActivity.class);
                String desc = movieEntity.getOverview();
                intent.putExtra(DetailFilmActivity.DESC,desc);
                intent.putExtra(DetailFilmActivity.MODEL,modelMovie);
                context.startActivity(intent);
            }
        });
    }
}
