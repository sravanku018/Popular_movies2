package com.example.subramanyam.popular_movies2;

/**
 * Created by subramanyam on 14-03-2018.
 */

import android.os.Parcel;
import android.os.Parcelable;
public class MovieData implements Parcelable {
    int id;
    String title;
    String orginal_title;
    String release_date;
    String bacground_path;
    String overview;
    String poster_path;
    String  vote_average;


    public MovieData() {

    }

    private MovieData(Parcel parcel) {
        this.id = parcel.readInt();
        this.title = parcel.readString();
        this.orginal_title = parcel.readString();
        this.release_date = parcel.readString();
        this.bacground_path = parcel.readString();
        this.overview = parcel.readString();
        this.poster_path = parcel.readString();
        this.vote_average = parcel.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrginal_title() {
        return orginal_title;
    }

    public void setOrginal_title(String orginal_title) {
        this.orginal_title = orginal_title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getBacground_path() {
        return bacground_path;
    }

    public void setBacground_path(String bacground_path) {
        this.bacground_path = bacground_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.orginal_title);
        parcel.writeString(this.release_date);
        parcel.writeString(this.bacground_path);
        parcel.writeString(this.overview);
        parcel.writeString(this.poster_path);
        parcel.writeString(this.getVote_average());

    }

    public static final Creator<MovieData> CREATOR = new Creator<MovieData>() {
        @Override
        public MovieData createFromParcel(Parcel parcel) {
            return new MovieData(parcel);
        }

        @Override
        public MovieData[] newArray(int i) {
            return new MovieData[i];
        }
    };
}
