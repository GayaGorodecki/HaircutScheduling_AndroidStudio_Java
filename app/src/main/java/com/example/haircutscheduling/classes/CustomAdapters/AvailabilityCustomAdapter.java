package com.example.haircutscheduling.classes.CustomAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.classes.DataModels.HairStyleDataModel;
import com.example.haircutscheduling.fragments.SelectAppointmentsFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AvailabilityCustomAdapter extends RecyclerView.Adapter<AvailabilityCustomAdapter.MyViewHolder>  {

    private final ArrayList<String> dataSet;
    private HairStyleDataModel hairStyleDataModel;
    private FirebaseDatabase database;

    public AvailabilityCustomAdapter(ArrayList<String> data, HairStyleDataModel hairStyleAppointment) {
        this.dataSet = data;
        this.hairStyleDataModel = hairStyleAppointment;
        database = FirebaseDatabase.getInstance();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewAvailable;
        TextView textViewHour;
        Button set;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.set = (Button) itemView.findViewById(R.id.buttonSet);
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

        Button btnSet = holder.set;

        String hour = dataSet.get(position).toString();
        textViewHour.setText(hour);

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hairStyleDataModel.setHour(hour);

                DatabaseReference myRef = database.getReference("appointments").child("appointmentsList");
                myRef.child(hairStyleDataModel.getDate()).child(hairStyleDataModel.getHour()).setValue(hairStyleDataModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
