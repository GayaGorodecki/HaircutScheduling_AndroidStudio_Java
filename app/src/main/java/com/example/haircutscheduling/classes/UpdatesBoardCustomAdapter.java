package com.example.haircutscheduling.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haircutscheduling.R;

import java.util.ArrayList;

public class UpdatesBoardCustomAdapter extends RecyclerView.Adapter<UpdatesBoardCustomAdapter.MyViewHolder> {

    private final ArrayList<UpdateDataModel> dataSet;

    public UpdatesBoardCustomAdapter(ArrayList<UpdateDataModel> data) {
        this.dataSet = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewUpdates;
        EditText editTextUpdate;
        TextView textViewUpdateDAte;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.cardViewUpdates = (CardView) itemView.findViewById(R.id.card_view_updates);
            this.editTextUpdate = (EditText) itemView.findViewById(R.id.editTextTextMultiLineUpdate);
            this.textViewUpdateDAte = (TextView) itemView.findViewById(R.id.textViewUpdateDate);
        }
    }

        @NonNull
        @Override
        public UpdatesBoardCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.updates_cards, parent, false);

            UpdatesBoardCustomAdapter.MyViewHolder myViewHolder = new UpdatesBoardCustomAdapter.MyViewHolder(view);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull UpdatesBoardCustomAdapter.MyViewHolder holder, int position) {


            EditText editTextUpdate = holder.editTextUpdate;
            TextView textViewUpdateDate = holder.textViewUpdateDAte;

            editTextUpdate.setText(dataSet.get(position).getUpdate());
            textViewUpdateDate.setText(dataSet.get(position).getDate());
        }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }

}
