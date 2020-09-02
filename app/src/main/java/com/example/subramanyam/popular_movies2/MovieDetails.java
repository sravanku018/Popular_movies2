package com.example.subramanyam.popular_movies2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.subramanyam.popular_movies2.Data.FavMovDBHelper;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.github.ivbaranov.mfb.MaterialFavoriteButton.OnFavoriteChangeListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetails extends Activity {


    static public ArrayList<String> movieTrailre;
ArrayList<TrailerData> trailerData;
    MovieData movie1;
    private FavMovDBHelper favMovDBHelper;
    private final MovieDetails activity=MovieDetails.this;
    private MovieData favorite;

    com.example.subramanyam.popular_movies2.TrailersAdapter trailersAdapter;


   public static ArrayList<String> movieReviewe;
   com.example.subramanyam.popular_movies2.ReviewAdapter reviewAdapter;
 int pos,position;

    static public int movID;
    static public int rating1;

    @BindView(R.id.movieName) ImageView imageView;
    @BindView(R.id.movieTitle) TextView title;
    @BindView(R.id.releaseDate) TextView releasedate;
    @BindView(R.id.overView) TextView review;
    @BindView(R.id.userRating) TextView rating;
    @BindView(R.id.trailerR) RecyclerView recyclerView;
    @BindView(R.id.reviewR) RecyclerView  recyclerView1;
    @BindView(R.id.favButton) MaterialFavoriteButton favorite1;







    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movieTrailre=new ArrayList<>();

        movieReviewe=new ArrayList<>();
        trailerData=new ArrayList<>();
        ButterKnife.bind(this);








      position= (Integer) getIntent().getIntExtra("id",pos);

      Picasso.with(getApplicationContext()).load(MainActivity.movieUrl.get(position)).into(imageView);
      title.setText("Title" +":"+ MainActivity.images.get(position).getTitle());
      releasedate.setText("Release Date"+":"+MainActivity.images.get(position).getRelease_date());
      review.setText(MainActivity.images.get(position).getOverview());
      rating.setText(MainActivity.images.get(position).getVote_average());
movID=MainActivity.images.get(position).getId();

final SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

Log.i("sdfdsf", String.valueOf(sharedPreferences.contains("Removed Favorite")));
        favorite1.setOnFavoriteChangeListener(

                new OnFavoriteChangeListener() {
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView,boolean favorite) {
                      if(favorite)
                        {
                          favorite1.isFavorite();


                            SharedPreferences.Editor editor=  sharedPreferences.edit();
                            editor.putBoolean("Added Favorite",true);
                            editor.apply();
                            saveFavorite();

                            Snackbar.make(buttonView,"Added to Favorite",Snackbar.LENGTH_LONG).show();
                        }else {
                          int movieid1= movID;
                          favMovDBHelper=new FavMovDBHelper(MovieDetails.this);
                          favMovDBHelper.deleteFavorite(movieid1);
                          SharedPreferences.Editor editor=sharedPreferences.edit();
                          editor.putBoolean("Removed Favorite",false);
                          editor.apply();

                          Snackbar.make(buttonView,"Removed from Favorites",Snackbar.LENGTH_LONG).show();
                      }
                    }
                });

        TrailerAsync2 trailerAsync2=new TrailerAsync2();
        try {
            trailerAsync2.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        TrailerAsync1 trailerAsync1=new TrailerAsync1();
        try {
            trailerAsync1.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }
    @SuppressLint("StaticFieldLeak")
    public class TrailerAsync2 extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... strings) {


            String url = "http://api.themoviedb.org/3/movie/" +movID + "/videos?api_key=4401cada7d6069e0e1e47e792a7768a0";


            com.example.subramanyam.popular_movies2.GetExample example = new com.example.subramanyam.popular_movies2.GetExample();
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
            movieTrailre.clear();



            if (results != null) {
                try {
                    JSONObject movies = new JSONObject(results);

                    JSONArray movieArray = movies.getJSONArray("results");


                    for (int i = 0; i < movieArray.length(); i++) {

                        TrailerData movie1 = new TrailerData();


                        JSONObject movie = movieArray.getJSONObject(i);
                        movie1.setKey(movie.getString("name"));


                        movie1.setKey(movie.getString("key"));






                        String text1 = "https://www.youtube.com/watch?v=" + movie1.getKey();
                        String text2=movie1.getName();
                        movieTrailre.add(text1);

                     for(int l=0;i<trailerData.size();i++)
                     {
                      String string=   trailerData.get(i).getName();
                      Log.i("zdvcx",string);
                     }


                        trailersAdapter =new com.example.subramanyam.popular_movies2.TrailersAdapter(getApplicationContext());
                        recyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView1.setAdapter(trailersAdapter);









                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        }
    }


    @SuppressLint("StaticFieldLeak")
    public class TrailerAsync1 extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... strings) {


            String url = "http://api.themoviedb.org/3/movie/" + movID + "/reviews?api_key=4401cada7d6069e0e1e47e792a7768a0";



            com.example.subramanyam.popular_movies2.GetExample example = new com.example.subramanyam.popular_movies2.GetExample();
            String response = null;
            try {
                response = example.run(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(response);
            Log.i("ffffs", response);

            return response;
        }

        @Override
        protected void onPostExecute(String results) {


            if (results != null) {
                try {
                    JSONObject movies = new JSONObject(results);

                    JSONArray movieArray = movies.getJSONArray("results");


                    for (int i = 0; i < movieArray.length(); i++) {

                        RevieewData revieewData=new RevieewData();


                 JSONObject movie = movieArray.getJSONObject(i);

  revieewData.setContent(movie.getString("content"));

                   Log.i("sds",revieewData.getContent());
                   String review=revieewData.getContent();
                   movieReviewe.add(review);

                        reviewAdapter=new com.example.subramanyam.popular_movies2.ReviewAdapter(getApplicationContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(reviewAdapter);


                        // textView.setText(text1);







                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        }
    }

    public void saveFavorite() {
        favMovDBHelper=new FavMovDBHelper(activity);
        favorite=new MovieData();
        int movieid=movID;
        String rating=MainActivity.images.get(pos).getVote_average();
        String poster=MainActivity.images.get(pos).getPoster_path();

        favorite.setId(movieid);
        favorite.setTitle(title.getText().toString().trim());
        favorite.setPoster_path(poster);
        favorite.setVote_average(String.valueOf(Double.parseDouble(rating)));
        favorite.setOverview(review.getText().toString().trim());

        favMovDBHelper.addFavorite(favorite);
    }

}



