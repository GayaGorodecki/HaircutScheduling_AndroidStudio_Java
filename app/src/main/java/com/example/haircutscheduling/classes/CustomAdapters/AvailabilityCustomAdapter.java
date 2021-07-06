package com.example.haircutscheduling.classes.CustomAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.classes.DataModels.HairStyleDataModel;

import java.util.ArrayList;
import java.util.Date;

public class AvailabilityCustomAdapter extends RecyclerView.Adapter<AvailabilityCustomAdapter.MyViewHolder>  {

    private final ArrayList<String> dataSet;

    public AvailabilityCustomAdapter(ArrayList<String> data) {
        this.dataSet = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewAvailable;
        TextView textViewHour;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.cardViewAvailable = (CardView) itemView.findViewById(R.id.available_card_view);
            this.textViewHour = (TextView) itemView.findViewById(R.id.textViewHour);
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

        TextView textViewHour = holder.textViewHour;
        CardView cardViewAvailable = holder.cardViewAvailable;

        textViewHour.setText(dataSet.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
