package com.example.subramanyam.popular_movies2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder> {

    private Context mContext;
    private int id;
   static ArrayList<String> trailers =new ArrayList<>();

    public TrailersAdapter(Context context,int posid)
    {
        this.mContext=context;
        this.id=posid;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trailers,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
holder.textView.setText(trailers.get(position));
Log.i("hgh",trailers.toString());
TrailerAsync1 trailerAsync1=new TrailerAsync1();
trailerAsync1.execute();
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
{
    TextView textView;

    public ViewHolder(View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.textView);
    }
}
    public class TrailerAsync1 extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... strings) {


            String url = "http://api.themoviedb.org/3/movie/" +MainActivity.images.get(id).getId() + "/videos?api_key=";
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
                        trailers.add(text1);







                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        }
    }
}
