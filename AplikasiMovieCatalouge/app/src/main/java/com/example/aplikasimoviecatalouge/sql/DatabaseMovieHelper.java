package com.example.aplikasimoviecatalouge.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseMovieHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "db_movie";
    public static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
            +"(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "%s TEXT NOT NULL,"+
            "%s TEXT NOT NULL,"+
            "%s TEXT NOT NULL,"+
            "%s TEXT NOT NULL)",
            DatabaseContract.TABLE_MOVIE,
            DatabaseContract.MovieColumn._ID,
            DatabaseContract.MovieColumn.ID,
            DatabaseContract.MovieColumn.TITLE,
            DatabaseContract.MovieColumn.POSTER,
            DatabaseContract.MovieColumn.OVERVIEW);

    public DatabaseMovieHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseContract.TABLE_MOVIE);
        onCreate(db);
    }
}
