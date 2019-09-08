package com.studio.faisal.moviecatalogue2.favoritemovie;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;




public class DatabaseContract {
    private DatabaseContract(){}
    public static final String TABLE_NAME = "favoritemovie";

    public static final class FavoriteMovie implements BaseColumns{
        public static final String COLUMN_MOVIEID = "movieid";
        public static final String COLUMN_JUDUL = "judul";
        public static final String COLUMN_TANGGAL = "tanggal";
        public static final String COLUMN_POSTER = "poster";
        public static final String COLUMN_SINOPSIS = "sinopsis";
    }
    public static final String AUTHORITY = "com.studio.faisal.moviecatalogue2";
    private static final String SCHEME = "content";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
