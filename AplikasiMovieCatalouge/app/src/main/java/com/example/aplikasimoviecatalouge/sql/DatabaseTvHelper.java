package com.example.aplikasimoviecatalouge.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseTvHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "db_tv";
    public static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_TV = String.format("CREATE TABLE %s"
                    +"(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL)",
            DatabaseContractTv.TABLE_TV,
            DatabaseContractTv.tvCoulumn._ID,
            DatabaseContractTv.tvCoulumn.ID,
            DatabaseContractTv.tvCoulumn.TITLE,
            DatabaseContractTv.tvCoulumn.POSTER,
            DatabaseContractTv.tvCoulumn.OVERVIEW);

    public DatabaseTvHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseContractTv.TABLE_TV);
        onCreate(db);
    }
}
