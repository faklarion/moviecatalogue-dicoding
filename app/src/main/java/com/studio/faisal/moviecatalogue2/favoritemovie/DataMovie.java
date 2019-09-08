package com.studio.faisal.moviecatalogue2.favoritemovie;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.getColumnInt;
import static com.studio.faisal.moviecatalogue2.favoritemovie.DatabaseContract.getColumnString;

public class DataMovie implements Parcelable {
    private String judul;
    private int id;
    private String tanggal;
    private String poster;
    private String sinopsis;

    public DataMovie(Cursor cursor) {
        this.id = getColumnInt(cursor, DatabaseContract.FavoriteMovie._ID);
        this.judul = getColumnString(cursor, DatabaseContract.FavoriteMovie.COLUMN_JUDUL);
        this.tanggal = getColumnString(cursor, DatabaseContract.FavoriteMovie.COLUMN_TANGGAL);
        this.poster = getColumnString(cursor, DatabaseContract.FavoriteMovie.COLUMN_POSTER);
        this.sinopsis = getColumnString(cursor, DatabaseContract.FavoriteMovie.COLUMN_SINOPSIS);

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public DataMovie() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.judul);
        dest.writeInt(this.id);
        dest.writeString(this.tanggal);
        dest.writeString(this.poster);
        dest.writeString(this.sinopsis);
    }

    protected DataMovie(Parcel in) {
        this.judul = in.readString();
        this.id = in.readInt();
        this.tanggal = in.readString();
        this.poster = in.readString();
        this.sinopsis = in.readString();
    }

    public static final Creator<DataMovie> CREATOR = new Creator<DataMovie>() {
        @Override
        public DataMovie createFromParcel(Parcel source) {
            return new DataMovie(source);
        }

        @Override
        public DataMovie[] newArray(int size) {
            return new DataMovie[size];
        }
    };
}
