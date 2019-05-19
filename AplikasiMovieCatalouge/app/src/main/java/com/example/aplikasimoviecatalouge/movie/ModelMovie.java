package com.example.aplikasimoviecatalouge.movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelMovie implements Parcelable {
    @SerializedName("id")
    @Expose
    private
    String idMovie;
    @SerializedName("title")
    @Expose
    private
    String name;
    @SerializedName("poster_path")
    @Expose
    private
    String poster_path;
    @SerializedName("overview")
    @Expose
    private
    String overview;

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return name;
    }

    public String getPostterMovie() {
        return poster_path;
    }

    public String getOverviewMovie() {
        return overview;
    }

    public ModelMovie() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
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

    private ModelMovie(Parcel in) {
        this.idMovie = in.readString();
        this.name = in.readString();
        this.poster_path = in.readString();
        this.overview = in.readString();
    }

    public static final Creator<ModelMovie> CREATOR = new Creator<ModelMovie>() {
        @Override
        public ModelMovie createFromParcel(Parcel source) {
            return new ModelMovie(source);
        }

        @Override
        public ModelMovie[] newArray(int size) {
            return new ModelMovie[size];
        }
    };
}
