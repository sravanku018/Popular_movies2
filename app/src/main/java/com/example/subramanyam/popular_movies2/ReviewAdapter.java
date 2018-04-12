package com.example.subramanyam.popular_movies2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private Context mContext;
   public static int id;
// private static    TextView textView = null;
 public static ArrayList<String> trailerData=new ArrayList<>();


    public LayoutInflater layoutInflater;

    public ReviewAdapter(Context context) {
        mContext = context;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detals, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(MovieDetails.movieReviewe.get(position));




    }


    @Override
    public int getItemCount() {
        return MovieDetails.movieReviewe.size();


    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;


        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.reviewD);





        }
    }


}
