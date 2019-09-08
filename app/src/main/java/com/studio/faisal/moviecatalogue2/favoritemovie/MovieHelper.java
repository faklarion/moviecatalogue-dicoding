package com.studio.faisal.moviecatalogue2.favoritemovie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.FavoriteMovie.COLUMN_JUDUL;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.FavoriteMovie.COLUMN_POSTER;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.FavoriteMovie.COLUMN_SINOPSIS;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.FavoriteMovie.COLUMN_TANGGAL;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.TABLE_NAME;

public class MovieHelper {
    private Context context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public MovieHelper(Context context){
        this.context = context;
    }

    public MovieHelper open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public ArrayList<DataMovie> getData(){
        Cursor cursor;
        cursor = database.query(TABLE_NAME,null,null,null,null,null,_ID+ " DESC",null);
        cursor.moveToFirst();

        ArrayList<DataMovie> arrayList = new ArrayList<>();
        DataMovie dataMovie;
        if (cursor.getCount()>0) {
            do {
                dataMovie = new DataMovie();
                dataMovie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                dataMovie.setJudul(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JUDUL)));
                dataMovie.setTanggal(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TANGGAL)));
                dataMovie.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POSTER)));
                dataMovie.setSinopsis(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SINOPSIS)));

                arrayList.add(dataMovie);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public boolean checkData(int id){
        Cursor cursor;
        cursor = database.rawQuery("select * from "+TABLE_NAME+" where "+_ID+" = "+id+"",null);
        cursor.moveToFirst();
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public Cursor queryByIdProvider(String id){
        return database.query(TABLE_NAME,null,_ID + " = ?",new String[]{id},null,null,null,null);
    }
    public Cursor queryProvider(){
        return database.query(TABLE_NAME,null,null,null,null,null,_ID + " DESC");
    }
    public long insertProvider(ContentValues values){
        return database.insert(TABLE_NAME,null,values);
    }
    public int updateProvider(String id,ContentValues values){
        return database.update(TABLE_NAME,values,_ID + " = '"+id+"'", null);
    }
    public int deleteProvider(String id){
        return database.delete(TABLE_NAME, _ID + " = '"+id+"'", null);
    }
}
