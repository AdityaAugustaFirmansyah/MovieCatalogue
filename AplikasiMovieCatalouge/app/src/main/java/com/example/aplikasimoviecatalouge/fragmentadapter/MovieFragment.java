package com.example.aplikasimoviecatalouge.fragmentadapter;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.os.ConfigurationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.example.aplikasimoviecatalouge.movie.ModelMovie;
import com.example.aplikasimoviecatalouge.movie.MovieContract;
import com.example.aplikasimoviecatalouge.movie.MoviePresenter;
import com.example.aplikasimoviecatalouge.rv.RvAdapterMovie;
import com.example.aplikasimoviecatalouge.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements MovieContract.ModelContractView {
    RecyclerView recyclerViewListMovies;
    ProgressBar progressBar;
    private ArrayList<ModelMovie> modelMovies1 = new ArrayList<>();
    Bundle bundle;
    String language;
    SearchView searchView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        recyclerViewListMovies = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.pb_movie);
        recyclerViewListMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        bundle = savedInstanceState;

        if (savedInstanceState !=null){
            modelMovies1 = savedInstanceState.getParcelableArrayList("movielist");
            RvAdapterMovie rvAdapterMovie= new RvAdapterMovie(getContext());
            rvAdapterMovie.setResults(modelMovies1);
            recyclerViewListMovies.setAdapter(rvAdapterMovie);
            recyclerViewListMovies.setHasFixedSize(true);
        }else{
            Locale locale = ConfigurationCompat.getLocales(getResources().getConfiguration()).get(0);
            language = locale.toString();
            Log.d("INIBAHASA",language);
            MoviePresenter moviePresenter = new MoviePresenter(MovieFragment.this,locale.toString(),progressBar,recyclerViewListMovies);
            moviePresenter.index();
        }
        return view;
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void listMovie(List<ModelMovie> modelMovies) {
        setHasOptionsMenu(true);
        RvAdapterMovie rvAdapterMovie = new RvAdapterMovie(getContext());
        rvAdapterMovie.setResults(modelMovies);
        modelMovies1.addAll(modelMovies);
        recyclerViewListMovies.setAdapter(rvAdapterMovie);
        recyclerViewListMovies.setHasFixedSize(true);
        recyclerViewListMovies.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("movielist",modelMovies1);
    }
}
