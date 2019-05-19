package com.example.aplikasimoviecatalouge.search;

import android.support.v4.os.ConfigurationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.aplikasimoviecatalouge.R;
import com.example.aplikasimoviecatalouge.movie.ModelMovie;
import com.example.aplikasimoviecatalouge.rv.RvAdapterMovie;
import com.example.aplikasimoviecatalouge.rv.RvTvAdapter;
import com.example.aplikasimoviecatalouge.tvshow.ModelTvShow;

import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity implements SearchContract.SearchContractView {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.rv_search);
        Locale locale = ConfigurationCompat.getLocales(getResources().getConfiguration()).get(0);
        String query = getIntent().getStringExtra("query");
        Log.d("INIJUDUL",query);
        SearchMoviePresenter searchMoviePresenter = new SearchMoviePresenter(query,locale.toString(),this);
        SearchTvPresenter searchTvPresenter = new SearchTvPresenter(query,locale.toString(),this);

        searchTvPresenter.index();
    }

    @Override
    public void msg(String msg) {
        Toast.makeText(SearchActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void listSearch(List<ModelTvShow> modelTvShowList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        RvTvAdapter rvTvAdapter = new RvTvAdapter();
        rvTvAdapter.setModelTvShows(modelTvShowList,SearchActivity.this);
        recyclerView.setAdapter(rvTvAdapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void listSearchMovie(List<ModelMovie> modelMovies) {
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        RvAdapterMovie rvAdapterMovie = new RvAdapterMovie(SearchActivity.this);
        rvAdapterMovie.setResults(modelMovies);
        recyclerView.setAdapter(rvAdapterMovie);
        recyclerView.setHasFixedSize(true);
    }
}
