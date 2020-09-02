package com.example.subramanyam.popular_movies2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private Context mContext;
   public static int id;
// private static    TextView textView = null;



    public LayoutInflater layoutInflater;

    public ReviewAdapter(Context context) {
        mContext = context;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detals, parent, false);
        ButterKnife.bind(this,view);
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
        @BindView(R.id.reviewD) TextView textView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);





        }
    }


}
