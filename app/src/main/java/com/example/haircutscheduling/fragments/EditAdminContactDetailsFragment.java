package com.example.haircutscheduling.fragments;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.activities.MainActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditAdminContactDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditAdminContactDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MainActivity mainActivity;

    public EditAdminContactDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditAdminContactDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditAdminContactDetailsFragment newInstance(String param1, String param2) {
        EditAdminContactDetailsFragment fragment = new EditAdminContactDetailsFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit_admin_contact_details, container, false);

        Button updatePhone = view.findViewById(R.id.buttonUpdateAdminPhone);
        updatePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EditText newPhone = view.findViewById(R.id.editTextAdminPhone);
                String phone = newPhone.getText().toString();
                mainActivity = (MainActivity) getActivity();

                if (phone.isEmpty())
                {
                    Toast.makeText(mainActivity, "Please enter new phone number.",Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences.Editor editor = mainActivity.getSharedPreferences(mainActivity.SHARED_PREFS_CONTACT, MODE_PRIVATE).edit();
                    editor.putString(mainActivity.PHONE, phone);
                    editor.apply();

                    Toast.makeText(mainActivity, "Phone updated!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button updateEmail = view.findViewById(R.id.buttonUpdateAdminEmail);
        updateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EditText newEmail = view.findViewById(R.id.editTextAdminEmailAddress);
                String email = newEmail.getText().toString();

                mainActivity = (MainActivity) getActivity();

                if (email.isEmpty())
                {
                    Toast.makeText(mainActivity, "Please enter new email address.",Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences.Editor editor = mainActivity.getSharedPreferences(mainActivity.SHARED_PREFS_CONTACT, MODE_PRIVATE).edit();
                    editor.putString(mainActivity.EMAIL, email);
                    editor.apply();

                    Toast.makeText(mainActivity, "Email updated!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button updateAddress = view.findViewById(R.id.buttonUpdateAdminAddress);
        updateAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText newAddress = view.findViewById(R.id.editTextAdminAddress);
                String address = newAddress.getText().toString();

                mainActivity = (MainActivity) getActivity();

                if (address.isEmpty())
                {
                    Toast.makeText(mainActivity, "Please enter new address.",Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences.Editor editor = mainActivity.getSharedPreferences(mainActivity.SHARED_PREFS_CONTACT, MODE_PRIVATE).edit();
                    editor.putString(mainActivity.ADDRESS, address);
                    editor.apply();

                    Toast.makeText(mainActivity, "Address updated!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}