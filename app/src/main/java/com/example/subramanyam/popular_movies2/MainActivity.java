package com.example.subramanyam.popular_movies2;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    static public ArrayList<MovieData> images;
    static public ArrayList<String> movieUrl;
    static public MovieData movieData;
    static  public ArrayList<String> movieTrailer;
    static  public RecyclerAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieData = new com.example.subramanyam.popular_movies2.MovieData();
        images=new ArrayList<>();
        movieUrl = new ArrayList<String>();
        movieTrailer=new ArrayList<>();
        recyclerAdapter=new RecyclerAdapter(this);


        if (isInternetOn()) {
            Netwrok netwrok;
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("api.themoviedb.org")
                    .path("/3/movie/popular")
                    .appendQueryParameter("api_key", "4401cada7d6069e0e1e47e792a7768a0")
                    .build();

            String Url = builder.toString();

            netwrok = new Netwrok();


            try {
                netwrok.execute(Url).get();



            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }


        }


    }


    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec != null) {
            if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                    connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                    connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                    connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

                // if connected with internet

                Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MoviegridView.class);

                startActivity(intent);


                return true;

            } else if (
                    connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                            connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {

                Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return false;
    }



}