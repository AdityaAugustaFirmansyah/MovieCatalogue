package com.example.aplikasimoviecatalouge.sql;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_MOVIE = "movie";

    static final class MovieColumn implements BaseColumns {
        static String ID = "idMovie";
        static String TITLE = "name";
        static String POSTER = "poster_path";
        static String OVERVIEW = "overview";
    }
}
