package com.example.aplikasimoviecatalouge.fragmentadapter;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplikasimoviecatalouge.R;
import com.example.aplikasimoviecatalouge.rv.RvAdapterTvFavorite;
import com.example.aplikasimoviecatalouge.sql.TvHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        TvHelper tvHelper = TvHelper.getINSTANCE(getContext());
        if (tvHelper.getDataTv().size()>0) {
            RecyclerView recyclerView = view.findViewById(R.id.rv_tv_show);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            RvAdapterTvFavorite rvAdapterTvFavorite = new RvAdapterTvFavorite(getActivity());
            rvAdapterTvFavorite.setTvEntities(tvHelper.getDataTv());
            recyclerView.setAdapter(rvAdapterTvFavorite);
            Log.d("JUMLAHDATA", String.valueOf(tvHelper.getDataTv().size()));
        }
        return view;
    }

}
