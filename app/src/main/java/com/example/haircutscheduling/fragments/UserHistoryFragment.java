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
import com.example.haircutscheduling.classes.CustomAdapters.BookedCustomAdapter;
import com.example.haircutscheduling.classes.DataModels.HairStyleDataModel;
import com.example.haircutscheduling.classes.CustomAdapters.HistoryCustomAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserHistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<HashMap<String, String>> historyData;
    private static HistoryCustomAdapter adapter;
    private FirebaseDatabase database;
    private FirebaseAuth mAuto;

    public UserHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserHistoryFragment newInstance(String param1, String param2) {
        UserHistoryFragment fragment = new UserHistoryFragment();
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
        View view = inflater.inflate(R.layout.fragment_user_history, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.historyRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DatabaseReference myRef = database.getReference("appointments").child("appointmentsList");
        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                historyData = new ArrayList<HashMap<String, String>>();

                String currentUserId = mAuto.getCurrentUser().getUid();
                if (!task.isSuccessful()) {
                } else {
                    if (task.getResult().hasChildren()) {

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
                        String today = dateFormat.format(new Date());
                        Date currentDay = null;
                        try {
                            currentDay = dateFormat.parse(today);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        Object objData = task.getResult().getValue(Object.class);
                        HashMap<String, HashMap<String,HashMap<String, String>>> appointmentMap = (HashMap<String, HashMap<String,HashMap<String, String>>>) objData;
                        for (HashMap<String,HashMap<String, String>> map : appointmentMap.values()) {

                            for (HashMap<String,String> val: map.values()) {

                                Date date = null;
                                try {
                                    date = dateFormat.parse(val.get("date"));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                if (val.get("userId").equals(currentUserId) && date.before(currentDay)) {
                                    historyData.add(val);
                                }
                            }
                        }
                    }
                    adapter = new HistoryCustomAdapter(historyData);
                    recyclerView.setAdapter(adapter);
                }
            }
        });

        // TODO:: sort list by hour\date?

        return view;
    }
}