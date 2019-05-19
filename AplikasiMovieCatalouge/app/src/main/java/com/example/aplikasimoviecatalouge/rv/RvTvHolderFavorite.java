package com.example.aplikasimoviecatalouge.rv;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aplikasimoviecatalouge.R;
import com.example.aplikasimoviecatalouge.detail.DetailFilmActivity;
import com.example.aplikasimoviecatalouge.detail.DetailTvActivity;
import com.example.aplikasimoviecatalouge.sql.MovieEntity;
import com.example.aplikasimoviecatalouge.sql.TvEntity;
import com.example.aplikasimoviecatalouge.tvshow.ModelTvShow;

public class RvTvHolderFavorite extends RecyclerView.ViewHolder {
    private TextView textView;
    private ImageView imageView;
    private RelativeLayout relativeLayout;
    public RvTvHolderFavorite(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.title_movie);
        imageView = itemView.findViewById(R.id.poster_movie);
        relativeLayout = itemView.findViewById(R.id.rl_item);
    }

    void bindItem(final TvEntity tvEntity, final Context context){
        textView.setText(tvEntity.getTitleTv());
        Glide.with(itemView).load("https://image.tmdb.org/t/p/w185/" + tvEntity.getPosterTv()).into(imageView);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelTvShow modelTvShow1 = new ModelTvShow();
                modelTvShow1.setIdTv(tvEntity.getIdTv());
                modelTvShow1.setTitleTv(tvEntity.getTitleTv());
                modelTvShow1.setPosterTv(tvEntity.getPosterTv());
                modelTvShow1.setDescTv(tvEntity.getDescTv());
                Intent intent = new Intent(context, DetailTvActivity.class);
                intent.putExtra(DetailTvActivity.MODEL, modelTvShow1);
                context.startActivity(intent);
            }
        });
    }
}
