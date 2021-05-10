package com.example.haircutscheduling.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haircutscheduling.R;

import java.util.ArrayList;

public class MainCustomAdapter extends RecyclerView.Adapter<MainCustomAdapter.MyViewHolder>{

    private final ArrayList<DataModel> dataSet;

    public MainCustomAdapter(ArrayList<DataModel> data)
    {
        this.dataSet = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView textViewHairStyle;
        TextView textViewPrice;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.cardView = (CardView) itemView.findViewById(R.id.card_view);
            this.textViewHairStyle = (TextView) itemView.findViewById(R.id.textViewHairStyle);
            this.textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageViewHairStyle);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.appoitment_main_cards, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView textViewHairStyle = holder.textViewHairStyle;
        TextView textViewDescription = holder.textViewPrice;
        ImageView imageView = holder.imageViewIcon;
        CardView cardView = holder.cardView;

        textViewHairStyle.setText(dataSet.get(position).getHairStyle());
        textViewDescription.setText(dataSet.get(position).getDescription());
        imageView.setImageResource(dataSet.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}