package com.example.haircutscheduling.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.activities.MainActivity;
import com.example.haircutscheduling.classes.Day;
import com.example.haircutscheduling.classes.Settings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditOpeningHour#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditOpeningHour extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MainActivity mainActivity;
    public FirebaseDatabase database;
    private String chosenDay;
    private boolean dayIsSelected;
    private final String[] days = {"sunday","monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};

    public EditOpeningHour() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditOpeningHour.
     */
    // TODO: Rename and change types and number of parameters
    public static EditOpeningHour newInstance(String param1, String param2) {
        EditOpeningHour fragment = new EditOpeningHour();
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
        View view = inflater.inflate(R.layout.fragment_edit_opening_hour, container, false);

        Spinner spinner = view.findViewById(R.id.dayChooseSpinner);
        mainActivity = (MainActivity) getActivity();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mainActivity, android.R.layout.simple_spinner_item, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                chosenDay = spinner.getSelectedItem().toString();
                dayIsSelected = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        EditText startTime = view.findViewById(R.id.editTextTimeStart);
        EditText endTime = view.findViewById(R.id.editTextTimeEnd);
        CheckBox dayOffCheckBox = view.findViewById(R.id.checkBoxDayOff);

        Button initOpeningHours = view.findViewById(R.id.buttonInitDaysHour);
        initOpeningHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startHour = startTime.getText().toString();
                String endHour = endTime.getText().toString();
                Boolean dayOff = dayOffCheckBox.isChecked();

                if (!dayIsSelected)
                {
                    Toast.makeText(mainActivity, "Please Choose day!", Toast.LENGTH_LONG).show();
                }
                else if (startHour.isEmpty())
                {
                    Toast.makeText(mainActivity, "Please Choose start hour!", Toast.LENGTH_LONG).show();
                }
                else if (endHour.isEmpty())
                {
                    Toast.makeText(mainActivity, "Please Choose end hour!", Toast.LENGTH_LONG).show();
                }
                else {
                    Day day = new Day(chosenDay,startHour,endHour,dayOff);
                    UpdateOpeningHour(day);
                }
            }
        });
        return view;
    }
    public void UpdateOpeningHour(Day day) {
        DatabaseReference myRef = database.getReference("settings");
        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                }
                else {
                    Settings settings = task.getResult().getValue(Settings.class);
                    myRef.child("OperationTime").child(convertDayStrToNum(day.getName())).setValue(day);
                    Toast.makeText(mainActivity, "Operation time updated successfully on ", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private String convertDayStrToNum(String day)
    {
        switch (day)
        {
            case "sunday":
                return "1";
            case "monday":
                return "2";
            case "tuesday":
                return "3";
            case "wednesday":
                return "4";
            case "thursday":
                return "5";
            case "friday":
                return "6";
            case "saturday":
                return "7";
            default:
                return "0";
        }
    }
}