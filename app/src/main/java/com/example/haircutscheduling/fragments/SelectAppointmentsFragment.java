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
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.activities.MainActivity;
import com.example.haircutscheduling.classes.AppointmentDay;
import com.example.haircutscheduling.classes.CustomAdapters.AvailabilityCustomAdapter;
import com.example.haircutscheduling.classes.Data.AppointmentsData;
import com.example.haircutscheduling.classes.DataModels.HairStyleDataModel;
import com.example.haircutscheduling.classes.Day;
import com.example.haircutscheduling.classes.Settings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectAppointmentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectAppointmentsFragment extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static AvailabilityCustomAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    String dayString, monthString, yearString;
    private FirebaseDatabase database;
    MainActivity mainActivity;
    HairStyleDataModel hairStyleAppointment;

    public SelectAppointmentsFragment() {}

    public SelectAppointmentsFragment(HairStyleDataModel hairStyle) {
        this.hairStyleAppointment = hairStyle;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment SelectAppointmentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectAppointmentsFragment newInstance(HairStyleDataModel hairStyle) {
        SelectAppointmentsFragment fragment = new SelectAppointmentsFragment(hairStyle);
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, hairStyle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            hairStyle = getArguments().getString(ARG_PARAM1);
        }
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_appointments, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerAvailbility);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        CalendarView dayOffCalender = view.findViewById(R.id.calendarViewSelectAppointment);
        dayOffCalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date  = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY).format(new Date(year,month,dayOfMonth));
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                int dayOfWeek = calendar.get(calendar.DAY_OF_WEEK);


                hairStyleAppointment.setDate(date);

                DatabaseReference myRef = database.getReference("appointments").child("appointmentsList");
                myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        ArrayList<String> hours = new ArrayList<>();

                        if (!task.isSuccessful()) {
                        }
                        else {
                            if (task.getResult().hasChildren()) {
                                AppointmentsData appointmentsData = task.getResult().getValue(AppointmentsData.class);
                                if (appointmentsData.appointmentList.containsValue(date)) {
                                    // TODO::  chage key - not date or change format
                                    hours = getAvailableHours(appointmentsData.appointmentList.get(date));

                                    adapter = new AvailabilityCustomAdapter(hours, hairStyleAppointment);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                            else {
                                DatabaseReference settingRef = database.getReference("settings");
                                settingRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        if (!task.isSuccessful()) {
                                        }
                                        else {
                                            if (task.getResult().hasChildren()) {
                                                ArrayList<String> hours = new ArrayList<>();
                                                Settings settings = task.getResult().getValue(Settings.class);
                                                if (settings.DayOffList.containsValue(date)) {
                                                    // TODO:: write 'not available day'...
                                                    mainActivity = (MainActivity) getActivity();
                                                    Toast.makeText(mainActivity, "Not available day", Toast.LENGTH_LONG).show();
                                                } else {
                                                    Day day = settings.OperationTime.get(String.valueOf(dayOfWeek));
                                                    AppointmentDay appointmentDay = new AppointmentDay(day);
                                                    hours = getAvailableHours(appointmentDay);

                                                    adapter = new AvailabilityCustomAdapter(hours, hairStyleAppointment);
                                                    recyclerView.setAdapter(adapter);
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });

        return view;
    }

    private ArrayList<String> getAvailableHours(AppointmentDay appointmentDay)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");

        ArrayList<String> availableHours = new ArrayList<String>();

        if (!appointmentDay.getDay().getDayOff()) {

            String startHour = appointmentDay.getDay().getStartHour();
            String endHour = appointmentDay.getDay().getEndHour();
            HashMap<String, HairStyleDataModel> booked = appointmentDay.getAppointList();

            Date start = null;
            Date end = null;

            try {
                start = dateFormat.parse(startHour);
                end = dateFormat.parse(endHour);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Date hour = start;

            while (hour.before(end)) {
                String hourFormat = dateFormat.format(hour.getTime());
                if (!booked.containsKey(hourFormat)) {
                    availableHours.add(hourFormat);
                }
                hour.setMinutes(hour.getMinutes() + 30);
            }
        }
        return availableHours;
    }
}