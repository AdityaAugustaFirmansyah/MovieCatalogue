package com.example.aplikasimoviecatalouge.rv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplikasimoviecatalouge.R;
import com.example.aplikasimoviecatalouge.sql.TvEntity;

import java.util.ArrayList;

public class RvAdapterTvFavorite extends RecyclerView.Adapter<RvTvHolderFavorite> {

    private ArrayList<TvEntity> tvEntities;
    private Context context;
    public void setTvEntities(ArrayList<TvEntity> tvEntities) {
        if (tvEntities.size()>0){
            this.tvEntities = tvEntities;
        }else {
            this.tvEntities.addAll(tvEntities);
            notifyDataSetChanged();
        }
    }

    public RvAdapterTvFavorite(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RvTvHolderFavorite onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        return new RvTvHolderFavorite(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvTvHolderFavorite rvTvHolderFavorite, int i) {
        rvTvHolderFavorite.bindItem(tvEntities.get(i),context);
    }

    @Override
    public int getItemCount() {
        return tvEntities.size();
    }
}
