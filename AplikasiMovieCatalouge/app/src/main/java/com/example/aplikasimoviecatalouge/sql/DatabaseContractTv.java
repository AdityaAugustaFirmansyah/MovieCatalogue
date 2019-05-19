package com.example.aplikasimoviecatalouge.sql;

import android.provider.BaseColumns;

public class DatabaseContractTv {
    static String TABLE_TV = "tv_show";

    static final class tvCoulumn implements BaseColumns {
        static String ID = "idTv";
        static String TITLE = "titleTv";
        static String POSTER = "posterTv";
        static String OVERVIEW = "descTv";
    }
}
