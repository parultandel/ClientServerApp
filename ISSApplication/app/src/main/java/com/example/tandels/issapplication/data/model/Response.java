package com.example.tandels.issapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Response Model Class
 */

public class Response implements Parcelable {
    @SerializedName("duration")
    private int duration;
    @SerializedName("risetime")
    private long risetime;

    public Response(Parcel in) {
        duration = in.readInt();
        risetime = in.readLong();
    }

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getRisetime() {
        return risetime;
    }

    public void setRisetime(long risetime) {
        this.risetime = risetime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(duration);
        parcel.writeLong(risetime);
    }
}
