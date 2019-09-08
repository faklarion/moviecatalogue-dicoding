package com.studio.faisal.moviecatalogue2.favoritetv;

import android.os.Parcel;
import android.os.Parcelable;

public class DataTV implements Parcelable {
    private String judul;
    private int id;
    private String tanggal;
    private String poster;
    private String sinopsis;

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public DataTV() {
    }

    protected DataTV(Parcel in) {
        this.judul = in.readString();
        this.id = in.readInt();
        this.tanggal = in.readString();
        this.poster = in.readString();
        this.sinopsis = in.readString();
    }

    public static final Parcelable.Creator<DataTV> CREATOR = new Parcelable.Creator<DataTV>() {
        @Override
        public DataTV createFromParcel(Parcel source) {
            return new DataTV(source);
        }

        @Override
        public DataTV[] newArray(int size) {
            return new DataTV[size];
        }
    };
}
