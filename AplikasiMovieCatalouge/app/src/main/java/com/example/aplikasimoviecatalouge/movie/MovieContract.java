package com.example.aplikasimoviecatalouge.movie;

import java.util.List;

public interface MovieContract {

    interface ModelContractView {
        void showError(String msg);

        void listMovie(List<ModelMovie> modelMovies);
    }

    interface ModelContractPresenter {
        void index();
    }
}
