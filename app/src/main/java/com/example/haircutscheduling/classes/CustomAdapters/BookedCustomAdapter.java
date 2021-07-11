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
import java.util.HashMap;

public class BookedCustomAdapter extends RecyclerView.Adapter<BookedCustomAdapter.MyViewHolder> {

    private final ArrayList<HashMap<String, String>> dataSet;

    public BookedCustomAdapter(ArrayList<HashMap<String, String>> data) {
        this.dataSet = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewBooked;
        TextView textViewHairStyleBooked;
        TextView textViewPriceBooked;
        TextView textViewDateBooked;
        TextView textViewHourBooked;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.cardViewBooked = (CardView) itemView.findViewById(R.id.card_view_booked);
            this.textViewHairStyleBooked = (TextView) itemView.findViewById(R.id.textViewHairStyleBooked);
            this.textViewPriceBooked = (TextView) itemView.findViewById(R.id.textViewPriceBooked);
            this.textViewDateBooked = (TextView) itemView.findViewById(R.id.textViewBookedDate);
            this.textViewHourBooked = (TextView) itemView.findViewById(R.id.textViewHourBooked);
        }
    }

    @NonNull
    @Override
    public BookedCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booked_cards, parent, false);

        BookedCustomAdapter.MyViewHolder myViewHolder = new BookedCustomAdapter.MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookedCustomAdapter.MyViewHolder holder, int position) {

        TextView textViewHairStyleBooked = holder.textViewHairStyleBooked;
        TextView textViewPriceBooked = holder.textViewPriceBooked;
        CardView cardViewBooked = holder.cardViewBooked;
        TextView textViewDateBooked = holder.textViewDateBooked;
        TextView textViewHourBooked = holder.textViewHourBooked;

//        textViewHairStyleBooked.setText(dataSet.get(position).getHairStyle());
//        textViewPriceBooked.setText(dataSet.get(position).getPrice());
//        textViewDateBooked.setText(dataSet.get(position).getDate());
//        textViewHourBooked.setText(dataSet.get(position).getHour());

        textViewHairStyleBooked.setText(dataSet.get(position).get("hairStyle"));
        textViewPriceBooked.setText(dataSet.get(position).get("price"));
        textViewDateBooked.setText(dataSet.get(position).get("date"));
        textViewHourBooked.setText(dataSet.get(position).get("hour"));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}