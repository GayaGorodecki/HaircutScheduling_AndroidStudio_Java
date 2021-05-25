package com.example.haircutscheduling.classes.BookedAppointments;

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

public class BookedCustomAdapter extends RecyclerView.Adapter<BookedCustomAdapter.MyViewHolder> {

    private final ArrayList<BookedDataModel> dataSet;

    public BookedCustomAdapter(ArrayList<BookedDataModel> data) {
        this.dataSet = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewBooked;
        TextView textViewHairStyleBooked;
        TextView textViewPriceBooked;
        TextView textViewDateBooked;
        TextView textViewHourBooked;
        ImageView imageViewIconBooked;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.cardViewBooked = (CardView) itemView.findViewById(R.id.card_view_booked);
            this.textViewHairStyleBooked = (TextView) itemView.findViewById(R.id.textViewHairStyleBooked);
            this.textViewPriceBooked = (TextView) itemView.findViewById(R.id.textViewPriceBooked);
            this.textViewDateBooked = (TextView) itemView.findViewById(R.id.textViewBookedDate);
            this.textViewHourBooked = (TextView) itemView.findViewById(R.id.textViewHourBooked);
            this.imageViewIconBooked = (ImageView) itemView.findViewById(R.id.imageViewHairStyleBooked);
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
        ImageView imageViewBooked = holder.imageViewIconBooked;
        CardView cardViewBooked = holder.cardViewBooked;
        TextView textViewDateBooked = holder.textViewDateBooked;
        TextView textViewHourBooked = holder.textViewHourBooked;

        textViewHairStyleBooked.setText(dataSet.get(position).getHairStyle());
        textViewPriceBooked.setText(dataSet.get(position).getPrice());
        textViewDateBooked.setText(dataSet.get(position).getDate());
        textViewHourBooked.setText(dataSet.get(position).getHour());
        imageViewBooked.setImageResource(dataSet.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}