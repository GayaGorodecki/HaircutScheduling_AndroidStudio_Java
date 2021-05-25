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
import com.example.haircutscheduling.classes.HairStylesMenu.HairStyleDataModel;

import java.util.ArrayList;

public class AvailabilityCustomAdapter extends RecyclerView.Adapter<AvailabilityCustomAdapter.MyViewHolder>  {

    private final ArrayList<HairStyleDataModel> dataSet;

    public AvailabilityCustomAdapter(ArrayList<HairStyleDataModel> data) {
        this.dataSet = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewBooked;
        TextView textViewHairStyleBooked;
        TextView textViewPriceBooked;
        ImageView imageViewIconBooked;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.cardViewBooked = (CardView) itemView.findViewById(R.id.available_card_view);
            this.textViewHairStyleBooked = (TextView) itemView.findViewById(R.id.textViewHairStyleBooked);
            this.textViewPriceBooked = (TextView) itemView.findViewById(R.id.textViewPriceBooked);
            this.imageViewIconBooked = (ImageView) itemView.findViewById(R.id.imageViewHairStyleBooked);
        }
    }

    @NonNull
    @Override
    public AvailabilityCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.available_card, parent, false);

        AvailabilityCustomAdapter.MyViewHolder myViewHolder = new AvailabilityCustomAdapter.MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AvailabilityCustomAdapter.MyViewHolder holder, int position) {

        TextView textViewHairStyleBooked = holder.textViewHairStyleBooked;
        TextView textViewDescriptionBooked = holder.textViewPriceBooked;
        ImageView imageViewBooked = holder.imageViewIconBooked;
        CardView cardViewBooked = holder.cardViewBooked;

        /*textViewHairStyleBooked.setText(dataSet.get(position).getHairStyle());
        textViewDescriptionBooked.setText(dataSet.get(position).getDescription());
        imageViewBooked.setImageResource(dataSet.get(position).getImage());*/
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
