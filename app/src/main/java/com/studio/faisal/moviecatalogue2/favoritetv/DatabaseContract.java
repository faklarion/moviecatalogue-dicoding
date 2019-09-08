package com.studio.faisal.moviecatalogue2.favoritetv;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static final class FavoriteMovie implements BaseColumns{
        public static final String TABLE_NAME = "favoritetv";
        public static final String COLUMN_MOVIEID = "movieid";
        public static final String COLUMN_JUDUL = "judul";
        public static final String COLUMN_TANGGAL = "tanggal";
        public static final String COLUMN_POSTER = "poster";
        public static final String COLUMN_SINOPSIS = "sinopsis";
    }
}
