package com.example.subramanyam.popular_movies2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder> {

    private Context mContext;
    private int id;


    public TrailersAdapter(Context context)
    {
        this.mContext=context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trailers,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textView.setText(MovieDetails.movieTrailre.get(position));





    }

    @Override
    public int getItemCount() {
        return MovieDetails.movieTrailre.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
{
    TextView textView;


    public ViewHolder(View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.trailerD);



    }
}

}
