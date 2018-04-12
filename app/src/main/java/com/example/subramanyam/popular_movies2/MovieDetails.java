package com.example.subramanyam.popular_movies2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MovieDetails extends Activity {
    ImageView imageView;
    public static MovieData movie1;
    static public ArrayList<String> movieTrailre;

    RecyclerView recyclerView;

    RecyclerView recyclerView1;
    TrailersAdapter trailersAdapter;
   public static ArrayList<TrailerData> arrayList;

   public static ArrayList<String> movieReviewe;
   ReviewAdapter reviewAdapter;
    int pos,position;

    static public int movID;

    TextView title,releasedate,review,rating,trailerT,reviewR;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movieTrailre=new ArrayList<>();
        arrayList=new ArrayList<>();
        movieReviewe=new ArrayList<>();


        imageView=findViewById(R.id.movieName);
        title=findViewById(R.id.movieTitle);
        releasedate=findViewById(R.id.releaseDate);
        review=findViewById(R.id.overView);
        rating=findViewById(R.id.userRating);



      position= (Integer) getIntent().getIntExtra("id",pos);




      Picasso.with(getApplicationContext()).load(MainActivity.movieUrl.get(position)).into(imageView);
      title.setText(MainActivity.images.get(position).getTitle());
      releasedate.setText(MainActivity.images.get(position).getRelease_date());
      review.setText(MainActivity.images.get(position).getOverview());
      rating.setText(MainActivity.images.get(position).getVote_average());
movID=MainActivity.images.get(position).getId();


        TrailerAsync2 trailerAsync2=new TrailerAsync2();
        try {
            trailerAsync2.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        TrailerAsync1 trailerAsync1=new TrailerAsync1();
        try {
            trailerAsync1.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
    public class TrailerAsync2 extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... strings) {


            String url = "http://api.themoviedb.org/3/movie/" +movID + "/videos?api_key=";


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
                        movieTrailre.add(text1);
                        recyclerView1=findViewById(R.id.trailerR);
                        trailersAdapter =new TrailersAdapter(getApplicationContext());
                        recyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView1.setAdapter(trailersAdapter);









                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        }
    }


    public class TrailerAsync1 extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... strings) {


            String url = "http://api.themoviedb.org/3/movie/" + movID + "/reviews?api_key=";



            GetExample example = new GetExample();
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
                        recyclerView= findViewById(R.id.reviewR);
                        reviewAdapter=new ReviewAdapter(getApplicationContext());
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


    }



