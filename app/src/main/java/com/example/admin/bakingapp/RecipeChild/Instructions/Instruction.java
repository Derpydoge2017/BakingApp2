package com.example.admin.bakingapp.RecipeChild.Instructions;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntegerRes;

/**
 * Created by Admin on 20-May-17.
 */

public class Instruction implements Parcelable {

    private String shortDescription;
    private String longDescription;
    private Integer id;
    private String videoURL;
    private String thumbnailURL;

    public Instruction() {

    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Integer getInstructionId() {
        return id;
    }

    public void setInstructionId(Integer id) {
        this.id = id;
    }


    public String getThumbnailURL(){
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }



    public String getVideoURL(){
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }




    protected Instruction(Parcel in) {
        shortDescription = in.readString();
        longDescription = in.readString();
        id = in.readInt();
        videoURL = in.readString();
        thumbnailURL = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shortDescription);
        dest.writeString(longDescription);
        dest.writeInt(id);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Instruction> CREATOR = new Parcelable.Creator<Instruction>() {
        @Override
        public Instruction createFromParcel(Parcel in) {
            return new Instruction(in);
        }

        @Override
        public Instruction[] newArray(int size) {
            return new Instruction[size];
        }
    };
}
