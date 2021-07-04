package com.example.haircutscheduling.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageUsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageUsersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MainActivity mainActivity;

    public ManageUsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManageUsersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManageUsersFragment newInstance(String param1, String param2) {
        ManageUsersFragment fragment = new ManageUsersFragment();
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
        View view = inflater.inflate(R.layout.fragment_manage_users, container, false);

        EditText phoneT = view.findViewById(R.id.editTextPhoneToManage);

        Button deleteUser = view.findViewById(R.id.buttonDeleteUser);
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = phoneT.getText().toString();

                mainActivity = (MainActivity) getActivity();

                if (phone.isEmpty())
                {
                    Toast.makeText(mainActivity, "Please enter user's phone", Toast.LENGTH_LONG).show();
                }
                else {
                    mainActivity.deleteUser(phone);
                }
            }
        });

        Button blockUser = view.findViewById(R.id.buttonBlockUser);
        blockUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = phoneT.getText().toString();

                mainActivity = (MainActivity) getActivity();

                if (phone.isEmpty())
                {
                    Toast.makeText(mainActivity, "Please enter user's phone", Toast.LENGTH_LONG).show();
                }
                else {
                    mainActivity.blockUser(phone);
                }

            }
        });

        return view;
    }
}