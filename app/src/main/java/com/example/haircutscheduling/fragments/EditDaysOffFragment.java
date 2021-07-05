package com.example.haircutscheduling.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.activities.MainActivity;
import com.example.haircutscheduling.classes.Settings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditDaysOffFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditDaysOffFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MainActivity mainActivity;
    public FirebaseDatabase database;

    String currentDate;

    public EditDaysOffFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditDaysOffFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditDaysOffFragment newInstance(String param1, String param2) {
        EditDaysOffFragment fragment = new EditDaysOffFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit_days_off, container, false);
        CalendarView dayOffCalender = view.findViewById(R.id.calendarViewDaysOff);
        dayOffCalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                currentDate  = DateFormat.getDateInstance(DateFormat.SHORT).format(new Date(year,month,dayOfMonth));
            }
        });

        Button addDaysOff = view.findViewById(R.id.buttonAddDayOff);
        addDaysOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                if (currentDate == null)
                {
                    Toast.makeText(mainActivity, "Please choose Date", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    addDayOff(currentDate);
                }
            }
        });

        Button cancelDaysOff = view.findViewById(R.id.buttonCancelDayOff);
        cancelDaysOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                if (currentDate == null)
                {
                    Toast.makeText(mainActivity, "Please choose Date", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    cancelDayOff(currentDate);
                }
            }
        });

        return view;
    }

    public void addDayOff(String date) {
        DatabaseReference myRef = database.getReference("settings");
        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                }
                else {
                    Settings settings = task.getResult().getValue(Settings.class);
                    if(!settings.DayOffList.containsValue(date)) {
                        Toast.makeText(mainActivity, "Day Off updated successfully on " + date, Toast.LENGTH_LONG).show();
                        myRef.child("DayOffList").push().setValue(date);
                    }
                    else{
                        Toast.makeText(mainActivity, "This is already Day Off " + date, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void cancelDayOff(String date) {
        DatabaseReference myRef = database.getReference("settings");
        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                }
                else {
                    Settings settings = task.getResult().getValue(Settings.class);
                    if(!settings.DayOffList.containsValue(date)) {
                        Toast.makeText(mainActivity, "This is not Day Off " + date, Toast.LENGTH_LONG).show();
                    }
                    else{
                        String keyToRemove = "";
                        for (String key: settings.DayOffList.keySet())
                        {
                            if (date.equals(settings.DayOffList.get(key))) {
                                keyToRemove = key;
                                break;
                            }
                        }
                        myRef.child("DayOffList").child(keyToRemove).removeValue();
                        Toast.makeText(mainActivity, "Day Off "+date +" Removed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}