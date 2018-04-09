package com.example.subramanyam.popular_movies2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by subramanyam on 22-03-2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context mContext;
   public LayoutInflater layoutInflater;
    private AdapterView.OnItemClickListener onItemClickListener;

public  RecyclerAdapter(Context context)
{
    mContext=context;
    this.layoutInflater =LayoutInflater.from(context);
}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        Picasso.with(mContext).load(MainActivity.movieUrl.get(position)).fit().into(holder.imageView);
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }

        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


             Intent intent=new Intent(mContext,MovieDetails.class);

                //  intent.putParcelableArrayListExtra("image_id",MainActivity.movieUrl.get(position));
                intent.putExtra("id",position);

                mContext.startActivity(intent);




            }
        });
    }



    @Override
    public int getItemCount() {
        return MainActivity.images.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
  public    ImageView imageView;
  public ImageButton imageButton;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.movieImages);
            imageButton=(ImageButton) itemView.findViewById(R.id.imageButton);
        }
    }
}
