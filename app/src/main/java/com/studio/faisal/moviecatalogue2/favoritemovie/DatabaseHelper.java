package com.studio.faisal.moviecatalogue2.favoritemovie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.FavoriteMovie.COLUMN_JUDUL;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.FavoriteMovie.COLUMN_MOVIEID;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.FavoriteMovie.COLUMN_POSTER;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.FavoriteMovie.COLUMN_SINOPSIS;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.FavoriteMovie.COLUMN_TANGGAL;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "favorite.db";
    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_FAVORITE = "create table "+ TABLE_NAME + " (" +
            _ID +" integer primary key autoincrement, " +
            COLUMN_JUDUL +" text not null, " +
            COLUMN_TANGGAL +" text not null, " +
            COLUMN_POSTER +" text not null, " +
            COLUMN_SINOPSIS +" text not null );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
