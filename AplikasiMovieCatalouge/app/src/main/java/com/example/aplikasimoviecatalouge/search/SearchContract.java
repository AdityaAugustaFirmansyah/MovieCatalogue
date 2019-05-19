package com.example.aplikasimoviecatalouge.search;

import com.example.aplikasimoviecatalouge.movie.ModelMovie;
import com.example.aplikasimoviecatalouge.tvshow.ModelTvShow;

import java.util.List;

public interface SearchContract {
    interface SearchContractPresenter{
        void index();
    }

    interface SearchContractView{
        void msg(String msg);
        void listSearch(List<ModelTvShow>modelTvShowList);
        void listSearchMovie(List<ModelMovie>modelMovies);
    }
}
