package com.example.aplikasimoviecatalouge.sql;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TvEntity implements Parcelable {
    private
    String idTv;
    private
    String titleTv;
    private
    String posterTv;
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

    public TvEntity() {
    }

    protected TvEntity(Parcel in) {
        this.idTv = in.readString();
        this.titleTv = in.readString();
        this.posterTv = in.readString();
        this.descTv = in.readString();
    }

    public static final Parcelable.Creator<TvEntity> CREATOR = new Parcelable.Creator<TvEntity>() {
        @Override
        public TvEntity createFromParcel(Parcel source) {
            return new TvEntity(source);
        }

        @Override
        public TvEntity[] newArray(int size) {
            return new TvEntity[size];
        }
    };
}
