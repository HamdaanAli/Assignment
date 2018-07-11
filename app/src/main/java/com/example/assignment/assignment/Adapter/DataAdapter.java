package com.example.assignment.assignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assignment.assignment.FullScreenActivity;
import com.example.assignment.assignment.R;
import com.example.assignment.assignment.model.Worldpopulation;

import java.util.ArrayList;

/**
 * Created by User on 11-Jul-18.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Worldpopulation> modelArrayList;
    public Context context;
    public DataAdapter(ArrayList<Worldpopulation>modelArrayList)
    {
        this.modelArrayList=modelArrayList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list,parent,false);
        context=parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    holder.rank.setText(modelArrayList.get(position).getRank());
    holder.country.setText(modelArrayList.get(position).getCountry());
    holder.population.setText(modelArrayList.get(position).getPopulation());
    String imageuri=holder.setFlag_image(modelArrayList.get(position).getFlag());
    holder.flag_image.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent n=new Intent(context,FullScreenActivity.class);
            n.putExtra("uri",imageuri);
            context.startActivity(n);

        }
    });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView rank,country,population;
        private ImageView flag_image;
        public ViewHolder(View itemView) {
            super(itemView);
            rank=itemView.findViewById(R.id.rank);
            country=itemView.findViewById(R.id.countery);
            population=itemView.findViewById(R.id.population);

        }
        public String setFlag_image(String image)
        {
            flag_image=itemView.findViewById(R.id.flag_img);
            Glide.with(context).load(image).into(flag_image);
            return image;
        }
    }
}
