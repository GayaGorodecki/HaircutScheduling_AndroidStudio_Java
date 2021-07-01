package com.example.haircutscheduling.fragments;

import android.graphics.ImageDecoder;
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
import com.example.haircutscheduling.classes.DataModels.UpdateDataModel;
import com.example.haircutscheduling.classes.CustomAdapters.UpdatesBoardCustomAdapter;
import com.example.haircutscheduling.classes.Data.UpdatesData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdatesBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdatesBoardFragment extends Fragment {

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
    private static ArrayList<UpdateDataModel> updatesData;
    private static UpdatesBoardCustomAdapter adapter;

    public UpdatesBoardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdatesBoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdatesBoardFragment newInstance(String param1, String param2) {
        UpdatesBoardFragment fragment = new UpdatesBoardFragment();
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
        View view = inflater.inflate(R.layout.fragment_updates_board, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.updatesRecycleView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mainActivity = (MainActivity) getActivity();

//        TODO:: get data from db (Updates)
        updatesData = new ArrayList<UpdateDataModel>();
        for (int i = 0; i < UpdatesData.updatesArray.length; i++) {
            updatesData.add(new UpdateDataModel(
                    UpdatesData.updatesArray[i],
                    UpdatesData.datesArray[i]
//                    UpdatesData.id[i]
            ));
        }

        adapter = new UpdatesBoardCustomAdapter(updatesData);
        recyclerView.setAdapter(adapter);

        return view;
    }
}