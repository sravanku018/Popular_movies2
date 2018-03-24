package com.example.subramanyam.popular_movies2;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by subramanyam on 15-03-2018.
 */
public class GetExample {
    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}