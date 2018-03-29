package com.example.subramanyam.popular_movies2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MovDetView extends RecyclerView.Adapter<MovDetView.ViewHolder>{
    private Context mContext;
   private int id;




    public LayoutInflater layoutInflater;
    public MovDetView(Context context)
    {
        mContext=context;
        this.layoutInflater=LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.detals,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {


        Picasso.with(mContext).load(MainActivity.movieUrl.get(id)).fit().into(holder.imageView);
        holder.movTitle.setText(MainActivity.images.get(id).getTitle());
        holder.movReview.setText(MainActivity.images.get(id).getOrginal_title());
        holder.movRating.setText(MainActivity.images.get(id).getVote_average());
        holder.movReleaseDate.setText(MainActivity.images.get(id).getRelease_date());
    }




    @Override
    public int getItemCount() {
        return 0;
    }

public static class ViewHolder extends RecyclerView.ViewHolder
{
 ImageView imageView;
TextView movTitle;
TextView movReleaseDate;
TextView movRating;
TextView movReview;
TextView textView;


    public ViewHolder(View itemView) {
        super(itemView);
        imageView=(ImageView) itemView.findViewById(R.id.movieName);
        movTitle=(TextView) itemView.findViewById(R.id.movieTitle);
        movRating=(TextView) itemView.findViewById(R.id.userRating);
        movReleaseDate=(TextView) itemView.findViewById(R.id.releaseDate);
        movReview=(TextView) itemView.findViewById(R.id.overView);
        textView=(TextView) itemView.findViewById(R.id.trailerD);

    }
}

    public class TrailerAsync1 extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {



            String url = "http://api.themoviedb.org/3/movie/" + MainActivity.images.get(id).getId() + "/videos?api_key=";
            Log.i("ja", String.valueOf(MainActivity.images.get(id).getId()));


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

                        String text1 = "https://www.youtube.com/watch?v=" + movie1.getKey();




                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
}
}