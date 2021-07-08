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

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.classes.CustomAdapters.AvailabilityCustomAdapter;
import com.example.haircutscheduling.classes.CustomAdapters.BookedCustomAdapter;
import com.example.haircutscheduling.classes.DataModels.HairStyleDataModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ScheduledExecutorService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookedAppoitmentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookedAppoitmentsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<HairStyleDataModel> bookedAppointmentData;
    private static BookedCustomAdapter adapter;
    private FirebaseDatabase database;
    private FirebaseAuth mAuto;

    public BookedAppoitmentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookedAppoitmentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookedAppoitmentsFragment newInstance(String param1, String param2) {
        BookedAppoitmentsFragment fragment = new BookedAppoitmentsFragment();
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
        mAuto = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booked_appoitments, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerBooked);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DatabaseReference myRef = database.getReference("appointments").child("appointmentsList");
        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                bookedAppointmentData = new ArrayList<HairStyleDataModel>();

                DatabaseReference myRef = database.getReference("appointments").child("appointmentsList");
                myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                                      @Override
                                                      public void onComplete(@NonNull Task<DataSnapshot> appointmentTask) {
                   String currentUserId = mAuto.getCurrentUser().getUid();
                   if (!appointmentTask.isSuccessful()) {

                   } else {
                       if (appointmentTask.getResult().hasChildren()) {
                           Object objData = appointmentTask.getResult().getValue(Object.class);
                           HashMap<String, HashMap<String,HairStyleDataModel>> appointmentMap = (HashMap<String, HashMap<String,HairStyleDataModel>>) objData;
                           /*
                           for (HashMap<String, HairStyleDataModel> map : appointmentMap.values()) {
                               HashMap<String, HashMap<String, String>> appointmentDay = (HashMap<String, HashMap<String, String>) map;
                               for (HashMap<String, String> dataModel : appointmentDay.values()) {
                                   String modelId = dataModel.get("userId");
                                   if (modelId.equals(currentUserId))
                                       bookedAppointmentData.add();
                               }
                           }*/
                       }
                       adapter = new BookedCustomAdapter(bookedAppointmentData);
                       recyclerView.setAdapter(adapter);
                   }}
                });

                // TODO:: search on myRef for specific user's appointments
                // TODO:: (if possible - get only future dates)
                // TODO:: and add them to the list
            }
        });

        return view;
    }
}