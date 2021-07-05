package com.example.haircutscheduling.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.activities.MainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MainActivity mainActivity;
    public FirebaseDatabase database;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {

        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button signin = view.findViewById(R.id.buttonSignin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                mainActivity.setFragment(new SigninFragment());
            }
        });

        Button login = view.findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView userNameTextView = view.findViewById(R.id.editTextTextEmailAddressLogin);
                TextView phoneTextView = view.findViewById((R.id.editTextPhoneLogin));
                TextView passwordTextView = view.findViewById(R.id.editTextTextPasswordLogin);

                String userName = userNameTextView.getText().toString();
                String phone = phoneTextView.getText().toString();
                String password = passwordTextView.getText().toString();

                mainActivity = (MainActivity) getActivity();

                if(userName.isEmpty() || password.isEmpty()) {
                    Toast.makeText(mainActivity, "Please enter user name and password", Toast.LENGTH_LONG).show();
                }
                else if (checkIfUserIsBlock(phone))
                {
                    Toast.makeText(mainActivity, "User is blocked. Cannot login.", Toast.LENGTH_LONG).show();
                }
                else {
                    mainActivity.Login(userName,password);
                }
            }
        });

        return view;
    }

    public boolean checkIfUserIsBlock(String phone) {
        DatabaseReference myRef = database.getReference("blockUsers");
        // TODO:: check if user found in 'blockUsers'
//        if (myRef.orderByChild("phone").get().getResult().hasChild(phone))
//        {
//            return true;
//        }
//        else {
//            return false;
//        }

        return false;
    }
}