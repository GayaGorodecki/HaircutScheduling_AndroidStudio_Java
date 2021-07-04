package com.example.haircutscheduling.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DownloadManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.classes.CustomAdapters.UpdatesBoardCustomAdapter;
import com.example.haircutscheduling.classes.DataModels.UpdateDataModel;
import com.example.haircutscheduling.classes.Day;
import com.example.haircutscheduling.classes.FirstEntry;
import com.example.haircutscheduling.classes.Settings;
import com.example.haircutscheduling.classes.User;
import com.example.haircutscheduling.fragments.AdminFragment;
import com.example.haircutscheduling.fragments.LoginFragment;
import com.example.haircutscheduling.fragments.MainFragment;
import com.example.haircutscheduling.fragments.SelectAppointmentsFragment;
import com.example.haircutscheduling.fragments.SigninFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.cert.PolicyQualifierInfo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private FirebaseAuth mAuth;
    public FirebaseDatabase database;

    private boolean savedUserFlag;
    private static boolean blockFlag;
    public static final String SHARED_PREFS_LOGIN = "loginSharedPrefs";
    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";

    public static final String SHARED_PREFS_CONTACT = "contactSharedPrefs";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        //  TODO:: save all last fragment data - with room \ SharedPreferences?

        fragmentManager = getSupportFragmentManager();

        if (FirstEntry.flag) {
            FirstEntry.flag = false;
            SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_LOGIN, MODE_PRIVATE);
            String userName = prefs.getString(USERNAME, "");
            String password = prefs.getString(PASSWORD, "");

            if (userName.equals("") || password.equals("")) {
                savedUserFlag = false;
                setLoginFragment();
            } else {
                savedUserFlag = true;
                Login(userName, password);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.findFragmentById(R.id.fragmentcon).getClass() == MainFragment.class ||
                fragmentManager.findFragmentById(R.id.fragmentcon).getClass() == AdminFragment.class) {
            FirstEntry.flag = true;
        }

        super.onBackPressed();
    }

    public void setLoginFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.replace(R.id.fragmentcon, loginFragment).commit();
    }

    public void Login(String userName, String password) {
        mAuth.signInWithEmailAndPassword(userName, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFS_LOGIN, MODE_PRIVATE).edit();
                            editor.putString(USERNAME, userName);
                            editor.putString(PASSWORD, password);
                            editor.apply();
                            setMainFragment();
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Successful Login", Toast.LENGTH_LONG).show();

                        } else {
                            if (userName.equals("admin") && password.equals("admin")) {
                                setFragment(new AdminFragment());
                                Toast.makeText(MainActivity.this, "Connect as Admin", Toast.LENGTH_LONG).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                setLoginFragment();
                                Toast.makeText(MainActivity.this, "Failed to Connect", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    public void Register(User user) {
        String userName = user.getEmail();
        String password = user.getPassword();
        mAuth.createUserWithEmailAndPassword(userName, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            DatabaseReference myRef = database.getReference("users").child(user.getPhone());
                            myRef.setValue(user);
                            Login(userName, password);
                        } else {
                            // If sign in fails, display a message to the user.
                            setFragment(new SigninFragment());
                            Toast.makeText(MainActivity.this, "Something goes wrong, Please try again.\nNote: A valid email and password with at least 6-characters are required.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void setMainFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();

        if (!savedUserFlag) {
            fragmentTransaction.replace(R.id.fragmentcon, mainFragment).addToBackStack(null).commit();
        } else {
            fragmentTransaction.replace(R.id.fragmentcon, mainFragment).commit();
        }
    }

    public void setFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentcon, fragment).addToBackStack(null).commit();
    }

    public void setSelectAppointmentsFragment(String hairStyle) {
        // TODO:: use parameter hairStyle for appointment
        fragmentTransaction = fragmentManager.beginTransaction();
        SelectAppointmentsFragment selectAppointmentsFragment = new SelectAppointmentsFragment();
        fragmentTransaction.replace(R.id.fragmentcon, selectAppointmentsFragment).addToBackStack(null).commit();
    }

    public void addDayOff(String date) {
        DatabaseReference myRef = database.getReference("settings");
        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                }
                else {
                    Settings settings = task.getResult().getValue(Settings.class);
                    if(!settings.DayOffList.containsValue(date)) {
                        Toast.makeText(MainActivity.this, "dayOff updated successfully on " + date, Toast.LENGTH_LONG).show();
                        myRef.child("DayOffList").push().setValue(date);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "This is already DayOff " + date, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void cancelDayOff(String date) {
        DatabaseReference myRef = database.getReference("settings");
        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                }
                else {
                    Settings settings = task.getResult().getValue(Settings.class);
                    if(!settings.DayOffList.containsValue(date)) {
                        Toast.makeText(MainActivity.this, "This is not Day Off " + date, Toast.LENGTH_LONG).show();
                    }
                    else{
                        String keyToRemove = "";
                        for (String key: settings.DayOffList.keySet())
                        {
                            if (date.equals(settings.DayOffList.get(key))) {
                                keyToRemove = key;
                                break;
                            }
                        }
                        myRef.child("DayOffList").child(keyToRemove).removeValue();
                        Toast.makeText(MainActivity.this, "Day Off "+date +" Removed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void addToUpdatesBoard(String update) {
        if (update.isEmpty()) {
            Toast.makeText(this, "Please write your update", Toast.LENGTH_SHORT).show();
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            String currentDate = sdf.format(new Date());
            UpdateDataModel adminUpdate = new UpdateDataModel(update, currentDate);
            DatabaseReference myRef = database.getReference("updates").child(adminUpdate.getDate());
            myRef.setValue(adminUpdate);

            Toast.makeText(this, "Update added to board", Toast.LENGTH_SHORT).show();
        }
    }

    public void logOut() {
        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFS_LOGIN, MODE_PRIVATE).edit();
        editor.putString(USERNAME, "");
        editor.putString(PASSWORD, "");
        editor.apply();
    }

    public void deleteUser(String phone) {

        DatabaseReference myRef = database.getReference("users").child(phone);

        if (myRef.getKey() != null) {
            myRef.removeValue();

            // TODO:: delete from authentication!
            //  or do something like 'block user'

            Toast.makeText(this, "User Deleted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void blockUser(String phone) {

        DatabaseReference myRef = database.getReference("blockUsers").child(phone);

        if (myRef.getKey() != null) { // TODO:: change 'getKey' / check if user found in block list already

            deleteUser(phone);
            User user = new User(phone);
            myRef.setValue(user);

            Toast.makeText(this, "User Blocked!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
        }
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

    public void UpdateOpeningHour() {
        DatabaseReference myRef = database.getReference("settings");
        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {

                }
                else {
                    Settings settings = task.getResult().getValue(Settings.class);
                    Day sunday = new Day("Sunday","09:00","17:00");
                    Day monday = new Day("Monday","09:00","17:00");
                    Day tuesday = new Day("tuesday","09:00","17:00");
                    Day wednesday = new Day("wednesday","09:00","17:00");
                    Day thursday = new Day("thursday","09:00","17:00");
                    Day friday = new Day("friday","09:00","17:00");
                    Day saturday = new Day("saturday","09:00","17:00");

                    myRef.child("OperationTime").push().setValue(sunday);
                    myRef.child("OperationTime").push().setValue(monday);
                    myRef.child("OperationTime").push().setValue(tuesday);
                    myRef.child("OperationTime").push().setValue(wednesday);
                    myRef.child("OperationTime").push().setValue(thursday);
                    myRef.child("OperationTime").push().setValue(friday);
                    myRef.child("OperationTime").push().setValue(saturday);
                    Toast.makeText(MainActivity.this, "Operation time updated successfully on ", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}