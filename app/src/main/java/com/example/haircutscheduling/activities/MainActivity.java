package com.example.haircutscheduling.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.classes.MainCustomAdapter;
import com.example.haircutscheduling.classes.DataModel;
import com.example.haircutscheduling.fragments.AppointmentsMainFragment;
import com.example.haircutscheduling.fragments.BookedAppoitmentsFragment;
import com.example.haircutscheduling.fragments.LoginFragment;
import com.example.haircutscheduling.fragments.MainFragment;
import com.example.haircutscheduling.fragments.SigninFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> hairstyleData;
    private static MainCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TODO:: 1. save last fragment on screen orientation change
//        TODO:: 2. Shared Preferences -> if user allready login or 'remember me' button

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
//          if (user == admin) {
//              setAdminFragment()
//          }
//          else {
                setMainFragment();
//          }
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

    public void setBookedAppoitmentsFragment()
    {
        fragmentTransaction = fragmentManager.beginTransaction();
        BookedAppoitmentsFragment bookedAppoitmentsFragment = new BookedAppoitmentsFragment();
        fragmentTransaction.replace(R.id.fragmentcon, bookedAppoitmentsFragment).addToBackStack(null).commit();
    }
}