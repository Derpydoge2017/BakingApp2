package com.example.admin.bakingapp.RecipeDisplay;


import android.os.Parcel;
import android.os.Parcelable;


public class Video implements Parcelable {

    private String url;

    public Video(String url) {
        this.url = url;
    }

    public Video(Parcel in) {
        url = in.readString();
    }

    public String getUrl() {
        return url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
    }

    public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {

        @Override
        public Video createFromParcel(Parcel parcel) {
            return new Video(parcel);
        }

        @Override
        public Video[] newArray(int i) {
            return new Video[i];
        }
    };
}
