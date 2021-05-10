package com.example.haircutscheduling.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.classes.CustomAdapter;
import com.example.haircutscheduling.classes.DataModel;
import com.example.haircutscheduling.classes.HairStylesData;
import com.example.haircutscheduling.fragments.AppointmentsMainFragment;
import com.example.haircutscheduling.fragments.LoginFragment;
import com.example.haircutscheduling.fragments.MainFragment;
import com.example.haircutscheduling.fragments.SigninFragment;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> hairstyleData;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TODO:: 1. save last fragment on screen orientation change
//        TODO:: 2. Shared Preferences -> if user allready login or 'remember me' button
//        TODO:: 3. check design in all screen size

        fragmentManager = getSupportFragmentManager();

        setLoginFragment();
    }

    public void setLoginFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.fragmentcon, loginFragment).commit();
    }

    public void setSigninFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        SigninFragment signinFragment = new SigninFragment();
        fragmentTransaction.replace(R.id.fragmentcon, signinFragment).addToBackStack(null).commit();
    }

    public void Login() {

//        TODO:: if(... check if user email & password is in DB...)
//        {
        setMainFragment();
//        }
    }

    public void Register() {
//        TODO:: register to DB
        setLoginFragment();
    }

    public void setMainFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        fragmentTransaction.replace(R.id.fragmentcon, mainFragment).addToBackStack(null).commit();
    }

    public void setAppoitmentsMainFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        AppointmentsMainFragment appointmentsMainFragment = new AppointmentsMainFragment();
        fragmentTransaction.replace(R.id.fragmentcon, appointmentsMainFragment).addToBackStack(null).commit();
    }
}