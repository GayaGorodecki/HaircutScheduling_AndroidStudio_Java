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
    String dayString, monthString, yearString;

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

        // TODO:: start day + end date?
        CalendarView dayOffCalender = view.findViewById(R.id.calendarViewDaysOff);
        dayOffCalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                dayString = String.valueOf(dayOfMonth);
                monthString = String.valueOf(month);
                yearString = String.valueOf(year);
            }
        });

        Button addDaysOff = view.findViewById(R.id.buttonAddDayOff);
        addDaysOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                if (dayString == null || monthString == null || yearString == null)
                {
                    Toast.makeText(mainActivity, "Please choose day", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mainActivity.addDayOff(dayString, monthString, yearString);
                }
            }
        });

        Button cancelDaysOff = view.findViewById(R.id.buttonCancelDayOff);
        cancelDaysOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                if (dayString == null || monthString == null || yearString == null)
                {
                    Toast.makeText(mainActivity, "Please choose day", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mainActivity.cancelDayOff(dayString, monthString, yearString);
                }
            }
        });

        return view;
    }
}