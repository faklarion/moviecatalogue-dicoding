package com.studio.faisal.moviecatalogue2.favoritetv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favoritetv.db";

    private static final int DATABASE_VERSION = 1;

    public static final String LOGTAG = "FAVORITE";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase db;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open(){
        Log.i(LOGTAG, "Database Opened");
        db = dbhandler.getWritableDatabase();
    }

    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + DatabaseContract.FavoriteMovie.TABLE_NAME + " (" +
                DatabaseContract.FavoriteMovie._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContract.FavoriteMovie.COLUMN_MOVIEID + " INTEGER, " +
                DatabaseContract.FavoriteMovie.COLUMN_JUDUL + " TEXT NOT NULL, " +
                DatabaseContract.FavoriteMovie.COLUMN_POSTER + " TEXT NOT NULL, " +
                DatabaseContract.FavoriteMovie.COLUMN_TANGGAL + " TEXT NOT NULL, " +
                DatabaseContract.FavoriteMovie.COLUMN_SINOPSIS + " TEXT NOT NULL" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.FavoriteMovie.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void addFavorite(DataTV dataTV){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.FavoriteMovie.COLUMN_JUDUL, dataTV.getJudul());
        values.put(DatabaseContract.FavoriteMovie.COLUMN_POSTER, dataTV.getPoster());
        values.put(DatabaseContract.FavoriteMovie.COLUMN_SINOPSIS, dataTV.getSinopsis());
        values.put(DatabaseContract.FavoriteMovie.COLUMN_TANGGAL, dataTV.getTanggal());
        values.put(DatabaseContract.FavoriteMovie.COLUMN_MOVIEID, dataTV.getId());
        db.insert(DatabaseContract.FavoriteMovie.TABLE_NAME, null, values);
        db.close();
    }

    public void deleteFavorite(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DatabaseContract.FavoriteMovie.TABLE_NAME, DatabaseContract.FavoriteMovie.COLUMN_MOVIEID+ "=" + id, null);
    }

    public List<DataTV> getAllFavorite(){
        String[] columns = {
                DatabaseContract.FavoriteMovie._ID,
                DatabaseContract.FavoriteMovie.COLUMN_MOVIEID,
                DatabaseContract.FavoriteMovie.COLUMN_JUDUL,
                DatabaseContract.FavoriteMovie.COLUMN_POSTER,
                DatabaseContract.FavoriteMovie.COLUMN_TANGGAL,
                DatabaseContract.FavoriteMovie.COLUMN_SINOPSIS
        };
        String sortOrder =
                DatabaseContract.FavoriteMovie._ID + " ASC";
        List<DataTV> favoriteList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DatabaseContract.FavoriteMovie.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()){
            do {
                DataTV dataTV = new DataTV();
                dataTV.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseContract.FavoriteMovie.COLUMN_MOVIEID))));
                dataTV.setJudul(cursor.getString(cursor.getColumnIndex(DatabaseContract.FavoriteMovie.COLUMN_JUDUL)));
                dataTV.setPoster(cursor.getString(cursor.getColumnIndex(DatabaseContract.FavoriteMovie.COLUMN_POSTER)));
                dataTV.setTanggal(cursor.getString(cursor.getColumnIndex(DatabaseContract.FavoriteMovie.COLUMN_TANGGAL)));
                dataTV.setSinopsis(cursor.getString(cursor.getColumnIndex(DatabaseContract.FavoriteMovie.COLUMN_SINOPSIS)));
                favoriteList.add(dataTV);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return favoriteList;
    }

}
