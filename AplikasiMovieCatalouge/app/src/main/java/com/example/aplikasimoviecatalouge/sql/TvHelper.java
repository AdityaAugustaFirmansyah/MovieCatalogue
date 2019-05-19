package com.example.aplikasimoviecatalouge.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class TvHelper {
    private static String DATABASE_TABLE = DatabaseContractTv.TABLE_TV;
    private static DatabaseTvHelper databaseTvHelper;
    private static SQLiteDatabase sqLiteDatabase;
    private static TvHelper INSTANCE;

    public TvHelper(Context context){
        databaseTvHelper = new DatabaseTvHelper(context);
    }

    public static TvHelper getINSTANCE(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new TvHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open()throws SQLException {
        sqLiteDatabase = databaseTvHelper.getWritableDatabase();
    }

    public void close(){
        databaseTvHelper.close();

        if (sqLiteDatabase.isOpen())
            sqLiteDatabase.close();
    }

    public ArrayList<TvEntity> getDataTv(){
        sqLiteDatabase = databaseTvHelper.getReadableDatabase();
        ArrayList<TvEntity> tvEntities = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE,null,null,null,null,null,
                DatabaseContractTv.tvCoulumn.ID + " ASC",null);
        cursor.moveToFirst();
        TvEntity tvEntity;
        if (cursor.getCount() > 0){
            Log.d("OKINIBISA3", String.valueOf(cursor.getCount()));
            do {
                tvEntity = new TvEntity();
                tvEntity.setIdTv(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractTv.tvCoulumn.ID)));
                tvEntity.setTitleTv(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractTv.tvCoulumn.TITLE)));
                tvEntity.setPosterTv(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractTv.tvCoulumn.POSTER)));
                tvEntity.setDescTv(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractTv.tvCoulumn.OVERVIEW)));
                tvEntities.add(tvEntity);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        Log.d("OKINIBISA2", String.valueOf(cursor.getColumnName(1)));
        cursor.close();
        return tvEntities;
    }

    public void insertData(TvEntity tvEntity){
        sqLiteDatabase = databaseTvHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContractTv.tvCoulumn.ID,tvEntity.getIdTv());
        contentValues.put(DatabaseContractTv.tvCoulumn.TITLE,tvEntity.getTitleTv());
        contentValues.put(DatabaseContractTv.tvCoulumn.POSTER,tvEntity.getPosterTv());
        contentValues.put(DatabaseContractTv.tvCoulumn.OVERVIEW,tvEntity.getDescTv());
        Log.d("DATAMASUK2",contentValues.toString());
        sqLiteDatabase.insertWithOnConflict(DATABASE_TABLE, null, contentValues,0);
    }


    public void deleteData(int id){
        sqLiteDatabase.delete(DATABASE_TABLE, DatabaseContractTv.tvCoulumn.ID + "= '" + id + "'", null);
    }

    public int cekFavorite(String id){
        sqLiteDatabase = databaseTvHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM "+ DATABASE_TABLE + " WHERE " + DatabaseContractTv.tvCoulumn.ID + " = '" + id +"'",null);
        cursor.moveToFirst();
        Log.d("MASUKNGGAK2", String.valueOf(cursor.getCount()));
        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            TvEntity tvEntity = new TvEntity();
            tvEntity.setIdTv(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractTv.tvCoulumn.ID)));
            tvEntity.setTitleTv(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractTv.tvCoulumn.TITLE)));
            tvEntity.setPosterTv(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractTv.tvCoulumn.POSTER)));
            tvEntity.setDescTv(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractTv.tvCoulumn.OVERVIEW)));
            cursor.moveToNext();
        }
        cursor.close();
        return cursor.getCount();
    }
}
