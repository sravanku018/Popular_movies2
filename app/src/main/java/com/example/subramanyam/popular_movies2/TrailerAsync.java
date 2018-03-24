package com.example.subramanyam.popular_movies2;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

/**
 * Created by subramanyam on 15-03-2018.
 */



public class TrailerAsync extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... strings) {

       String url= "";



        GetExample example = new GetExample();
        String response = null;
        try {
            response = example.run(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response);
        Log.i("s",response);
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}