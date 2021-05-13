package com.example.haircutscheduling.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.activities.MainActivity;
import com.example.haircutscheduling.classes.AvailabilityCustomAdapter;
import com.example.haircutscheduling.classes.BookedCustomAdapter;
import com.example.haircutscheduling.classes.DataModel;
import com.example.haircutscheduling.classes.HairStylesData;
import com.example.haircutscheduling.classes.MainCustomAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectAppointmentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectAppointmentsFragment extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> bookedAppointmentData;
    private static AvailabilityCustomAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerAvailbility);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        bookedAppointmentData = new ArrayList<DataModel>();
        for (int i = 0; i < HairStylesData.hairStyleArray.length; i++) {
            bookedAppointmentData.add(new DataModel(
                    HairStylesData.hairStyleArray[i],
                    HairStylesData.descriptationArray[i],
                    HairStylesData.id[i],
                    HairStylesData.drawableArray[i]
            ));
        }

        adapter = new AvailabilityCustomAdapter(bookedAppointmentData);
        recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }
}