package com.example.subramanyam.popular_movies2;

import android.os.Parcel;
import android.os.Parcelable;

public class RevieewData implements Parcelable {

    String author;
    String content;

    public RevieewData()
    {

    }
  private RevieewData(Parcel parcel)
  {
      this.author=parcel.readString();
      this.content=parcel.readString();

  }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static final Creator<RevieewData> CREATOR = new Creator<RevieewData>() {
        @Override
        public RevieewData createFromParcel(Parcel in) {
            return new RevieewData(in);
        }

        @Override
        public RevieewData[] newArray(int size) {
            return new RevieewData[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.author);
        parcel.writeString(this.content);

    }
}
