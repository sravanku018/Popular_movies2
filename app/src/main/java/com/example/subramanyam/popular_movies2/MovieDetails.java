package com.example.subramanyam.popular_movies2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MovieDetails extends Activity{
    ImageView imageView;
    public static MovieData movie1;
    ArrayList<TrailerData> dataArray;
    RecyclerView recyclerView;
    MovDetView movDetView;
    int position;

int  id=getIntent().getIntExtra("id",position);



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        recyclerView=(RecyclerView) findViewById(R.id.detailsR);
        movDetView=new MovDetView(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MovDetView(this));













    }




    }



