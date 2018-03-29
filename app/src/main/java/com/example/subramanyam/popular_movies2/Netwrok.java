package com.example.subramanyam.popular_movies2;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by subramanyam on 14-03-2018.
 */

public  class Netwrok  extends AsyncTask<String,Void,String> {


    @Override
    protected String doInBackground(String... strings) {
        final String REQUEST_METHOD = "GET";
        final int READ_TIMEOUT = 15000;
        final int CONNECTION_TIMEOUT = 15000;
        final String REQUEST1_METHOD = "POST";


        String url = strings[0];

        String results = null;
        String inputLine;

        try {
            URL mainurl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) mainurl.openConnection();
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setRequestMethod(REQUEST1_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.connect();

            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());

            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);


            }

            reader.close();
            inputStreamReader.close();
            results = stringBuilder.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("result", results);
        return results;


    }


    @Override
    protected void onPostExecute(String results) {


        MainActivity.images.clear();
        MainActivity.movieUrl.clear();


        if (results != null) {
            try {
                JSONObject movies = new JSONObject(results);

                JSONArray movieArray = movies.getJSONArray("results");


                for (int i = 1; i < movieArray.length(); i++) {

                    MovieData movie1 = new MovieData();


                    JSONObject movie = movieArray.getJSONObject(i);


                    movie1.setTitle(movie.getString("title"));
                    movie1.setId(movie.getInt("id"));
                    movie1.setOrginal_title(movie.getString("original_title"));
                    movie1.setBacground_path(movie.getString("backdrop_path"));
                    movie1.setOverview(movie.getString("overview"));

                    movie1.setPoster_path(movie.getString("poster_path"));
                    movie1.setVote_average(movie.getString("vote_average"));
                    movie1.setRelease_date(movie.getString("release_date"));


                    String posterUrl = "http://image.tmdb.org/t/p/w185/" + movie.getString("poster_path");

                    MainActivity.movieUrl.add(posterUrl);







                    Log.i("s", posterUrl);


                    MainActivity.images.add(movie1);
                    MoviegridView.recyclerAdapter.notifyDataSetChanged();



                }


            } catch (JSONException e) {
                e.printStackTrace();

            }

            Log.i("s", results);


        }


    }
}

