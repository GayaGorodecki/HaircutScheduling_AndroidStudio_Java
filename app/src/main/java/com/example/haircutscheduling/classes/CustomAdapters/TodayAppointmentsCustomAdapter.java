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

public class TodayAppointmentsCustomAdapter extends RecyclerView.Adapter<TodayAppointmentsCustomAdapter.MyViewHolder>{

    private final ArrayList<HairStyleDataModel> dataSet;

    public TodayAppointmentsCustomAdapter(ArrayList<HairStyleDataModel> data) {
        this.dataSet = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewTodaysBooked;
        TextView textViewHairStyleHistory;
        TextView textViewDateHistory;
        TextView textViewHourHistory;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.cardViewTodaysBooked = (CardView) itemView.findViewById(R.id.card_view_history);
            this.textViewHairStyleHistory = (TextView) itemView.findViewById(R.id.textViewHairStyleHistory);
            this.textViewDateHistory = (TextView) itemView.findViewById(R.id.textViewHistoryDate);
            this.textViewHourHistory = (TextView) itemView.findViewById(R.id.textViewHistoryHour);
        }
    }

    @NonNull
    @Override
    public TodayAppointmentsCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_cards, parent, false);

        TodayAppointmentsCustomAdapter.MyViewHolder myViewHolder = new TodayAppointmentsCustomAdapter.MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodayAppointmentsCustomAdapter.MyViewHolder holder, int position) {

        TextView textViewHairStyleHistory = holder.textViewHairStyleHistory;
        TextView textViewDateHistory = holder.textViewDateHistory;
        TextView textViewHourHistory = holder.textViewHourHistory;
        CardView cardViewHistory = holder.cardViewTodaysBooked;

        textViewDateHistory.setText(dataSet.get(position).getDate());
        textViewHourHistory.setText(dataSet.get(position).getHour());
        textViewHairStyleHistory.setText(dataSet.get(position).getHairStyle());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
