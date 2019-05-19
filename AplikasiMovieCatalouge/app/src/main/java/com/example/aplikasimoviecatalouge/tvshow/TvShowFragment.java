package com.example.aplikasimoviecatalouge.tvshow;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.os.ConfigurationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.aplikasimoviecatalouge.R;
import com.example.aplikasimoviecatalouge.rv.RvTvAdapter;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment implements TvContract.TvViewContract {
    ProgressBar progressBar;
    RecyclerView recyclerView;
    ArrayList<ModelTvShow> modelMovies1 = new ArrayList<>();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        recyclerView = view.findViewById(R.id.rv_tv_show);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (savedInstanceState != null) {
            modelMovies1 = savedInstanceState.getParcelableArrayList("tvlist");
            RvTvAdapter rvAdapterMovie = new RvTvAdapter();
            rvAdapterMovie.setModelTvShows(modelMovies1, getContext());
            recyclerView.setAdapter(rvAdapterMovie);
            recyclerView.setHasFixedSize(true);
        }else {
            progressBar = view.findViewById(R.id.pb_tv);
            Locale locale = ConfigurationCompat.getLocales(getResources().getConfiguration()).get(0);
            TvPresenter tvPresenter = new TvPresenter(TvShowFragment.this, locale.toString(), progressBar,recyclerView);
            tvPresenter.index();
        }
        return view;
    }

    @Override
    public void msg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void listTv(ModelResponse modelTvShows) {
        RvTvAdapter rvTvAdapter = new RvTvAdapter();
        rvTvAdapter.setModelTvShows(modelTvShows.getListTv(), getContext());
        modelMovies1.addAll(modelTvShows.getListTv());
        recyclerView.setAdapter(rvTvAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("tvlist", modelMovies1);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            savedInstanceState.getParcelableArrayList("tvlist");
        }
        super.onViewStateRestored(savedInstanceState);
    }
}
