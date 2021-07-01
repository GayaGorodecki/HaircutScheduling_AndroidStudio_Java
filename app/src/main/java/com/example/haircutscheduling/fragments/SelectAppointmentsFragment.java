package com.example.haircutscheduling.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.CalendarView;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.classes.CustomAdapters.AvailabilityCustomAdapter;
import com.example.haircutscheduling.classes.DataModels.HairStyleDataModel;
import com.example.haircutscheduling.classes.Data.HairStylesMenuData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectAppointmentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectAppointmentsFragment extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<HairStyleDataModel> bookedAppointmentData;
    private static AvailabilityCustomAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String dayString, monthString, yearString;

    public SelectAppointmentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectAppointmentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectAppointmentsFragment newInstance(String param1, String param2) {
        SelectAppointmentsFragment fragment = new SelectAppointmentsFragment();
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

        View view = inflater.inflate(R.layout.fragment_select_appointments, container, false);

        // TODO:: start day + end date?
        CalendarView dayOffCalender = view.findViewById(R.id.calendarViewSelectAppointment);
        dayOffCalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dayString = String.valueOf(dayOfMonth);
                monthString = String.valueOf(month);
                yearString = String.valueOf(year);

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerAvailbility);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        bookedAppointmentData = new ArrayList<HairStyleDataModel>();
        for (int i = 0; i < HairStylesMenuData.hairStyleArray.length; i++) {
            bookedAppointmentData.add(new HairStyleDataModel(
                    HairStylesMenuData.hairStyleArray[i],
                    HairStylesMenuData.priceArray[i],
                    HairStylesMenuData.id[i],
                    HairStylesMenuData.drawableArray[i]
            ));
        }

        adapter = new AvailabilityCustomAdapter(bookedAppointmentData);
        recyclerView.setAdapter(adapter);

        return view;
    }
}