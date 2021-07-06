package com.example.haircutscheduling.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.classes.AppointmentDay;
import com.example.haircutscheduling.classes.CustomAdapters.AvailabilityCustomAdapter;
import com.example.haircutscheduling.classes.Data.AppointmentsData;
import com.example.haircutscheduling.classes.DataModels.HairStyleDataModel;
import com.example.haircutscheduling.classes.Data.HairStylesMenuData;
import com.example.haircutscheduling.classes.Day;
import com.example.haircutscheduling.classes.Settings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Provider;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
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
    private FirebaseDatabase database;

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

                String date  = DateFormat.getDateInstance(DateFormat.SHORT).format(new Date(year,month,dayOfMonth));

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                int dayOfWeek = calendar.get(calendar.DAY_OF_WEEK);

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
                                    // TODO:: get available appointment list (hours) -- chage key not date
                                    hours = getAvailableHours(appointmentsData.appointmentList.get(date));

                                    adapter = new AvailabilityCustomAdapter(hours);
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
                                                } else {
                                                    Day day = settings.OperationTime.get(String.valueOf(dayOfWeek));
                                                    AppointmentDay appointmentDay = new AppointmentDay(day);
                                                    hours = getAvailableHours(appointmentDay);

                                                    adapter = new AvailabilityCustomAdapter(hours);
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
        String startHour = appointmentDay.getDay().getStartHour();
        String endHour = appointmentDay.getDay().getEndHour();
        ArrayList<HairStyleDataModel> booked = appointmentDay.getAppointList();

        Date start = null;
        Date end = null;

        try {
            start = dateFormat.parse(startHour);
            end = dateFormat.parse(endHour);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date hour = start;

        while (hour.before(end))
        {
            //TODO:: check if not exist in booked list
            availableHours.add(dateFormat.format(hour.getTime()));
            hour.setMinutes(hour.getMinutes() + 30);
        }

        return availableHours;
    }
}