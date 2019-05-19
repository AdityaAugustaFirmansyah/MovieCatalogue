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
import com.example.aplikasimoviecatalouge.rv.RvFavoriteMovieAdapter;
import com.example.aplikasimoviecatalouge.sql.MovieHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        MovieHelper movieHelper = MovieHelper.getINSTANCE(getContext());
        if (movieHelper.getDataMovie().size() > 0){
            RecyclerView recyclerView = view.findViewById(R.id.rv_movie);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            RvFavoriteMovieAdapter rvFavoriteMovieAdapter = new RvFavoriteMovieAdapter(getActivity());
            rvFavoriteMovieAdapter.setMovieEntities(movieHelper.getDataMovie());
            recyclerView.setAdapter(rvFavoriteMovieAdapter);
            Log.d("JUMLAHDATA2", String.valueOf(movieHelper.getDataMovie().get(0).getId()));
            Log.d("JUMLAHDATA", String.valueOf(movieHelper.getDataMovie().size()));
        }
        return view;
    }
}
