package com.example.aplikasimoviecatalouge.tvshow;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ModelTvShow implements Parcelable {
    @SerializedName("id")
    private
    String idTv;
    @SerializedName("name")
    private
    String titleTv;
    @SerializedName("poster_path")
    private
    String posterTv;
    @SerializedName("overview")
    private
    String descTv;

    public String getIdTv() {
        return idTv;
    }

    public void setIdTv(String idTv) {
        this.idTv = idTv;
    }

    public String getTitleTv() {
        return titleTv;
    }

    public void setTitleTv(String titleTv) {
        this.titleTv = titleTv;
    }

    public String getPosterTv() {
        return posterTv;
    }

    public void setPosterTv(String posterTv) {
        this.posterTv = posterTv;
    }

    public String getDescTv() {
        return descTv;
    }

    public void setDescTv(String descTv) {
        this.descTv = descTv;
    }


    public ModelTvShow() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idTv);
        dest.writeString(this.titleTv);
        dest.writeString(this.posterTv);
        dest.writeString(this.descTv);
    }

    protected ModelTvShow(Parcel in) {
        this.idTv = in.readString();
        this.titleTv = in.readString();
        this.posterTv = in.readString();
        this.descTv = in.readString();
    }

    public static final Creator<ModelTvShow> CREATOR = new Creator<ModelTvShow>() {
        @Override
        public ModelTvShow createFromParcel(Parcel source) {
            return new ModelTvShow(source);
        }

        @Override
        public ModelTvShow[] newArray(int size) {
            return new ModelTvShow[size];
        }
    };
}
