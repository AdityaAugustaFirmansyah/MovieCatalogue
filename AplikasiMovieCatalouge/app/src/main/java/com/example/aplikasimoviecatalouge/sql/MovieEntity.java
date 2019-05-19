package com.example.aplikasimoviecatalouge.sql;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieEntity implements Parcelable {
    private
    String idMovie;
    private
    String name;
    private
    String poster_path;
    private
    String overview;

    public String getId() {
        return idMovie;
    }

    public void setId(String id) {
        this.idMovie = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


    public MovieEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idMovie);
        dest.writeString(this.name);
        dest.writeString(this.poster_path);
        dest.writeString(this.overview);
    }

    protected MovieEntity(Parcel in) {
        this.idMovie = in.readString();
        this.name = in.readString();
        this.poster_path = in.readString();
        this.overview = in.readString();
    }

    public static final Creator<MovieEntity> CREATOR = new Creator<MovieEntity>() {
        @Override
        public MovieEntity createFromParcel(Parcel source) {
            return new MovieEntity(source);
        }

        @Override
        public MovieEntity[] newArray(int size) {
            return new MovieEntity[size];
        }
    };
}
