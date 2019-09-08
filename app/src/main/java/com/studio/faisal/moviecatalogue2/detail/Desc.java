package com.studio.faisal.moviecatalogue2.detail;

import android.os.Parcel;
import android.os.Parcelable;


public class Desc implements Parcelable {
    private String name, year, sino;

    public String getSino() {
        return sino;
    }

    public void setSino(String sino) {
        this.sino = sino;
    }

    public String photo;

    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static Creator<Desc> getCREATOR() {
        return CREATOR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }



    public Desc() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.year);
        dest.writeString(this.sino);
        dest.writeString(this.photo);
        dest.writeInt(this.id);
    }

    protected Desc(Parcel in) {
        this.name = in.readString();
        this.year = in.readString();
        this.sino = in.readString();
        this.photo = in.readString();
        this.id = in.readInt();
    }

    public static final Creator<Desc> CREATOR = new Creator<Desc>() {
        @Override
        public Desc createFromParcel(Parcel source) {
            return new Desc(source);
        }

        @Override
        public Desc[] newArray(int size) {
            return new Desc[size];
        }
    };
}
