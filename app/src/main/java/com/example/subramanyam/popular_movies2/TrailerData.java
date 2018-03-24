package com.example.subramanyam.popular_movies2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by subramanyam on 16-03-2018.
 */

public class TrailerData implements Parcelable {
    String key;

    public TrailerData()
    {

    }
    private TrailerData(Parcel parcel)
    {
        this.key=parcel.readString();
    }

public String getKey()
{
    return key;
}
public void setKey(String key)
{
    this.key=key;
}
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.key);

    }
    public static final Creator<TrailerData> CREATOR=new Creator<TrailerData>() {
        @Override
        public TrailerData createFromParcel(Parcel parcel) {
            return new TrailerData(parcel);
        }

        @Override
        public TrailerData[] newArray(int i) {
            return new TrailerData[i];
        }
    };
}
