package com.example.aplikasimoviecatalouge.tvshow;

public interface TvContract {
    interface TvPresenterContract{
        void index();
    }
    interface TvViewContract{
        void msg(String msg);
        void listTv(ModelResponse modelTvShows);
    }
}
