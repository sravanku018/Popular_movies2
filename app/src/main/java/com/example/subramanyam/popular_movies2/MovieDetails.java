package com.example.subramanyam.popular_movies2;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MovieDetails extends Activity{
    ImageView imageView;
    public static MovieData movie1;
    ArrayList<TrailerData> dataArray;


    TextView title, over_view, release_date, rating, trailer;
    Context context;
    int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        dataArray = new ArrayList<>();


        title = (TextView) findViewById(R.id.movieTitle);
        over_view = (TextView) findViewById(R.id.overView);
        release_date = (TextView) findViewById(R.id.releaseDate);
        rating = (TextView) findViewById(R.id.userRating);
        trailer = (TextView) findViewById(R.id.trailerD);


        imageView = (ImageView) findViewById(R.id.movieName);
        //ArrayList<String>  moviepos = (ArrayList<String>) getIntent().getExtras("image_id",MainActivity.movieUrl.size());
        int movie_id = (Integer) getIntent().getIntExtra("id", pos);


        Picasso.with(getApplicationContext()).load(MainActivity.movieUrl.get(movie_id)).fit().into(imageView);
        title.setText(MainActivity.images.get(movie_id).getOrginal_title());
        release_date.setText(MainActivity.images.get(movie_id).getRelease_date());
        rating.setText(String.valueOf(MainActivity.images.get(movie_id).getVote_average()));
        over_view.setText(MainActivity.images.get(movie_id).getOverview());


        TrailerAsync1 trailerAsync = new TrailerAsync1();
        trailerAsync.execute();


    }




    public class TrailerAsync1 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {


              String url = "http://api.themoviedb.org/3/movie/" + MainActivity.images.get(pos).getId() + "/videos?api_key=";
            //  String url1=MainActivity.movieTrailer.get(pos);


            GetExample example = new GetExample();
            String response = null;
            try {
                response = example.run(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(response);
            Log.i("s", response);

            return response;
        }

        @Override
        protected void onPostExecute(String results) {
            if (results != null) {
                try {
                    JSONObject movies = new JSONObject(results);

                    JSONArray movieArray = movies.getJSONArray("results");


                    for (int i = 1; i < movieArray.length(); i++) {

                        TrailerData movie1 = new TrailerData();


                        JSONObject movie = movieArray.getJSONObject(i);


                        movie1.setKey(movie.getString("key"));

                        String key = movie1.getKey();
                        String text1 = "https://www.youtube.com/watch?v=" + key;
                        trailer.setText(text1);



                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
    }
}