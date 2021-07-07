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
import com.example.haircutscheduling.classes.CustomAdapters.BookedCustomAdapter;
import com.example.haircutscheduling.classes.Data.BookedData;
import com.example.haircutscheduling.classes.DataModels.HairStyleDataModel;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

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

//        TODO:: get data from db (booked appoitments)
        // TODO:: take from user list and delete 'bookedData' ?
        bookedAppointmentData = new ArrayList<HairStyleDataModel>();
//        for (int i = 0; i < BookedData.hairStyleArray.length; i++) {
//            bookedAppointmentData.add(new HairStyleDataModel(
//                    BookedData.hairStyleArray[i],
//                    BookedData.priceArray[i],
//                    BookedData.dateArray[i],
//                    BookedData.hourArray[i],
//                    BookedData.id[i],
//                    BookedData.drawableArray[i]
//            ));
//        }

        adapter = new BookedCustomAdapter(bookedAppointmentData);
        recyclerView.setAdapter(adapter);

        return view;
    }
}