package com.example.haircutscheduling.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.haircutscheduling.classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SigninFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SigninFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseAuth mAuth;
    public FirebaseDatabase database;
    MainActivity mainActivity;

    public SigninFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SigninFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SigninFragment newInstance(String param1, String param2) {
        SigninFragment fragment = new SigninFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
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
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        Button register = view.findViewById(R.id.buttonRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nameTextView = view.findViewById(R.id.editTextPersonName);
                EditText emailTextView = view.findViewById(R.id.editTextTextEmailAddressSignin);
                EditText passwordTextView = view.findViewById(R.id.editTextTextPasswordSignin);
                EditText phoneTextView = view.findViewById(R.id.editTextPhone);

                String name = nameTextView.getText().toString();
                String email = emailTextView.getText().toString();
                String password = passwordTextView.getText().toString();
                String phone = phoneTextView.getText().toString();

                mainActivity = (MainActivity) getActivity();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty())
                {
                    Toast.makeText(mainActivity, "Please fill in all the required information", Toast.LENGTH_LONG).show();
                }
                else if (mainActivity.checkIfUserIsBlock(email))
                {
                    Toast.makeText(mainActivity, "User is blocked! Cannot register.", Toast.LENGTH_LONG).show();
                }
                else {
                    User user = new User(name, email, password, phone,false);
                    Register(user);
                }
            }
        });

        return view;
    }

    public void Register(User user) {
        String userName = user.getEmail();
        String password = user.getPassword();
        mAuth.createUserWithEmailAndPassword(userName, password)
                .addOnCompleteListener(mainActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            DatabaseReference myRef = database.getReference("users").child(user.getPhone());
                            myRef.setValue(user);
                            mainActivity.Login(userName, password);
                        } else {
                            // If sign in fails, display a message to the user.
                            mainActivity.setFragment(new SigninFragment());
                            Toast.makeText(mainActivity, "Something goes wrong, Please try again.\nNote: A valid email and password with at least 6-characters are required.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}