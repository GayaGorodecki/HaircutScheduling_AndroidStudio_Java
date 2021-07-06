package com.example.haircutscheduling.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.activities.MainActivity;
import com.example.haircutscheduling.classes.DataModels.UpdateDataModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditUpdatesBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditUpdatesBoardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MainActivity mainActivity;
    public FirebaseDatabase database;

    public EditUpdatesBoardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditUpdatesBoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditUpdatesBoardFragment newInstance(String param1, String param2) {
        EditUpdatesBoardFragment fragment = new EditUpdatesBoardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_updates_board, container, false);

        Button addUpdate = view.findViewById(R.id.buttonAddUpdate);
        addUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText updateInput = view.findViewById(R.id.editTextTextMultiLineUpdateInput);
                String update = updateInput.getText().toString();

                mainActivity = (MainActivity) getActivity();
                addToUpdatesBoard(update);
            }
        });

        return view;
    }

    public void addToUpdatesBoard(String update) {
        if (update.isEmpty()) {
            Toast.makeText(mainActivity, "Please write your update", Toast.LENGTH_SHORT).show();
        } else {
            String currentDate  = DateFormat.getDateInstance(DateFormat.SHORT).format(new Date());

            UpdateDataModel adminUpdate = new UpdateDataModel(update, currentDate);
            DatabaseReference myRef = database.getReference("updates");
            myRef.child("updatesList").push().setValue(adminUpdate);

            Toast.makeText(mainActivity, "Update added to board", Toast.LENGTH_SHORT).show();
        }
    }
}