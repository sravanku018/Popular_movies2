package com.example.subramanyam.popular_movies2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.subramanyam.popular_movies2.Data.FavMovDBHelper;

import java.util.ArrayList;

public class MoviegridView extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{
    GridView gridView;
    ImageView imageView;
    private ArrayList<MovieData> movieData;



    static public RecyclerView recyclerView;

    static public RecyclerAdapter recyclerAdapter;
    private AppCompatActivity activity=MoviegridView.this;
    private FavMovDBHelper favMovDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviegrid_view);




view1();

    }


    public void view1()
    {
        recyclerAdapter=new RecyclerAdapter(this);
        recyclerView=(RecyclerView) findViewById(R.id.viewMovies);


        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new RecyclerAdapter(this));
        favMovDBHelper=new FavMovDBHelper(activity);
checkSortOrder();
    }

    public void views2()
    {
        recyclerAdapter=new RecyclerAdapter(this);
        recyclerView=(RecyclerView) findViewById(R.id.viewMovies);


        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new RecyclerAdapter(this));
        favMovDBHelper=new FavMovDBHelper(activity);
getAllFavorites();
    }

    public void popilar()
    {
        
    }


    public void topRated()
{

    Netwrok netwrok = new Netwrok();
    netwrok.execute("http://api.themoviedb.org/3/movie/popular?api_key=4401cada7d6069e0e1e47e792a7768a0");

    Intent intent = new Intent(this, MoviegridView.class);
    startActivity(intent);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menuSettings:
            Intent intent=new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }



    }


    @Override
    public void onBackPressed() {

        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);

        startActivity(startMain);


    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
     checkSortOrder();
    }
    public void checkSortOrder()
    {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
         String sortOrder=sharedPreferences.getString(this.getString(R.string.pref_sort_order_key),
                 this.getString(R.string.popular));
         if (sortOrder.equals(this.getString(R.string.pref_most_popular)))
         {
         }

         else if(sortOrder.equals(this.getString(R.string.favorite)))
         {
             views2();

         } else
         {
             Netwrok netwrok = new Netwrok();
             netwrok.execute("http://api.themoviedb.org/3/movie/top_rated?api_key=4401cada7d6069e0e1e47e792a7768a0");

             Intent intent = new Intent(this, MoviegridView.class);
             startActivity(intent);
         }




    }


    @SuppressLint("StaticFieldLeak")
    public void getAllFavorites()
    {
     new AsyncTask<Void,Void,Void>(){

         @Override
         protected Void doInBackground(Void... voids) {
             MainActivity.images.clear();
             MainActivity.images.addAll(favMovDBHelper.getAllFavorite());
             return  null;
         }

         @Override
         protected void onPostExecute(Void aVoid) {
             super.onPostExecute(aVoid);
             MoviegridView.recyclerAdapter.notifyDataSetChanged();
         }
     }.execute();

    }
}