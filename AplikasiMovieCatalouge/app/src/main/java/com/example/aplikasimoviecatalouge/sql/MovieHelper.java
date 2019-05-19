package com.example.aplikasimoviecatalouge.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MovieHelper {
    private static String DATABASE_TABLE = DatabaseContract.TABLE_MOVIE;
    private static DatabaseMovieHelper databaseMovieHelper;
    private static SQLiteDatabase sqLiteDatabase;
    private static MovieHelper INSTANCE;

    public MovieHelper(Context context){
        databaseMovieHelper = new DatabaseMovieHelper(context);
    }

    public static MovieHelper getINSTANCE(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new MovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open()throws SQLException {
        sqLiteDatabase = databaseMovieHelper.getWritableDatabase();
    }

    public void close(){
        databaseMovieHelper.close();

        if (sqLiteDatabase.isOpen())
            sqLiteDatabase.close();
    }

    public ArrayList<MovieEntity> getDataMovie(){
        sqLiteDatabase = databaseMovieHelper.getReadableDatabase();
        ArrayList<MovieEntity> movieEntities = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE,null,null,null,null,null,
        DatabaseContract.MovieColumn.ID + " ASC",null);
        cursor.moveToFirst();
        MovieEntity movieEntity;
        if (cursor.getCount() > 0){
            Log.d("OKINIBISA", String.valueOf(cursor.getCount()));
            do {
                movieEntity = new MovieEntity();
                movieEntity.setId(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.ID)));
                movieEntity.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.TITLE)));
                movieEntity.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.POSTER)));
                movieEntity.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.OVERVIEW)));
                movieEntities.add(movieEntity);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        Log.d("OKINIBISA", String.valueOf(cursor.getColumnName(1)));
        cursor.close();
        return movieEntities;
    }

    public void insertData(MovieEntity movieEntity){
        sqLiteDatabase = databaseMovieHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.MovieColumn.ID,movieEntity.getId());
        contentValues.put(DatabaseContract.MovieColumn.TITLE,movieEntity.getName());
        contentValues.put(DatabaseContract.MovieColumn.POSTER,movieEntity.getPoster_path());
        contentValues.put(DatabaseContract.MovieColumn.OVERVIEW,movieEntity.getOverview());
        Log.d("DATAMASUK",contentValues.toString());
        sqLiteDatabase.insertWithOnConflict(DATABASE_TABLE, null, contentValues,0);
    }


    public void deleteData(int id){
        sqLiteDatabase.delete(DATABASE_TABLE, DatabaseContract.MovieColumn.ID + "= '" + id + "'", null);
    }

    public int cekFavorite(String id){
        sqLiteDatabase = databaseMovieHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM "+ DATABASE_TABLE + " WHERE " + DatabaseContract.MovieColumn.ID + " = '" + id +"'",null);
        cursor.moveToFirst();
        Log.d("MASUKNGGAK", String.valueOf(cursor.getCount()));
        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            MovieEntity movieEntity = new MovieEntity();
            movieEntity.setId(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.ID)));
            movieEntity.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.TITLE)));
            movieEntity.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.POSTER)));
            movieEntity.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.OVERVIEW)));
            cursor.moveToNext();
        }
        cursor.close();
        return cursor.getCount();
    }
}
