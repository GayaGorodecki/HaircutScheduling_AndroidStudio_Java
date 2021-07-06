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
import com.example.haircutscheduling.classes.CustomAdapters.HairStylesMenuCustomAdapter;
import com.example.haircutscheduling.classes.DataModels.HairStyleDataModel;
import com.example.haircutscheduling.classes.Data.HairStylesMenuData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentsMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointmentsMainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MainActivity mainActivity;

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<HairStyleDataModel> hairstyleData;
    private static HairStylesMenuCustomAdapter adapter;

    public AppointmentsMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppointmentsMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppointmentsMainFragment newInstance(String param1, String param2) {
        AppointmentsMainFragment fragment = new AppointmentsMainFragment();
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
        View view = inflater.inflate(R.layout.fragment_appointments_main, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.hairstyleRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        hairstyleData = new ArrayList<HairStyleDataModel>();
        for (int i = 0; i < HairStylesMenuData.hairStyleArray.length; i++) {
            hairstyleData.add(new HairStyleDataModel(
                    HairStylesMenuData.hairStyleArray[i],
                    HairStylesMenuData.priceArray[i],
                    HairStylesMenuData.drawableArray[i]
            ));
        }
        
        mainActivity = (MainActivity) getActivity();
        adapter = new HairStylesMenuCustomAdapter(hairstyleData,mainActivity);
        recyclerView.setAdapter(adapter);

        return view;
    }
}