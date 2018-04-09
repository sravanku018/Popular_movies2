package com.example.subramanyam.popular_movies2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MovieDetails extends Activity {
    ImageView imageView;
    public static MovieData movie1;
    ArrayList<TrailerData> dataArray;
    RecyclerView recyclerView;
    MovDetView movDetView;
    RecyclerView recyclerView1;
    TrailersAdapter trailersAdapter;
    int pos;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

      Intent intent=getIntent();
      int position= (Integer) getIntent().getIntExtra("id",pos);






      recyclerView=(RecyclerView) findViewById(R.id.detailsR);
        movDetView=new MovDetView(this,position);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movDetView);
        recyclerView.setNestedScrollingEnabled(false);
        movDetView.notifyItemChanged(position);


        recyclerView1=findViewById(R.id.trailerR);
      trailersAdapter =new TrailersAdapter(this,position);
         recyclerView1.setLayoutManager(new LinearLayoutManager(this));
         recyclerView1.setAdapter(trailersAdapter);


    }




    }



