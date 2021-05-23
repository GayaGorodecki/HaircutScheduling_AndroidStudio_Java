package com.example.haircutscheduling.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.classes.FirstEntry;
import com.example.haircutscheduling.classes.MainCustomAdapter;
import com.example.haircutscheduling.classes.DataModel;
import com.example.haircutscheduling.fragments.AdminFragment;
import com.example.haircutscheduling.fragments.AppointmentsMainFragment;
import com.example.haircutscheduling.fragments.BookedAppoitmentsFragment;
import com.example.haircutscheduling.fragments.LoginFragment;
import com.example.haircutscheduling.fragments.MainFragment;
import com.example.haircutscheduling.fragments.SelectAppointmentsFragment;
import com.example.haircutscheduling.fragments.SigninFragment;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> hairstyleData;
    private static MainCustomAdapter adapter;
    private boolean savedUserFlag;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  TODO:: save all last fragment data - with room \ SharedPreferences

        fragmentManager = getSupportFragmentManager();

        if (FirstEntry.flag == true)
        {
            FirstEntry.flag = false;

            SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            String userName = prefs.getString(USERNAME, "");
            String password = prefs.getString(PASSWORD, "");

            if (userName.equals("") || password.equals(""))
            {
                savedUserFlag = false;
                setLoginFragment();
            }
            else {
                savedUserFlag = true;
                Login(userName, password);
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        if (fragmentManager.findFragmentById(R.id.fragmentcon).getClass() == MainFragment.class)
        {
            FirstEntry.flag = true;
        }

        super.onBackPressed();
    }

    public void setLoginFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.fragmentcon, loginFragment).addToBackStack(null).commit();
    }

    public void setSigninFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        SigninFragment signinFragment = new SigninFragment();
        fragmentTransaction.replace(R.id.fragmentcon, signinFragment).addToBackStack(null).commit();
    }

    public void Login(String userName, String password) {

          //TODO:: if(... check if user email & password is in DB...)
          if (userName.equals("admin") && password.equals("admin")) {
              setAdminFragment();
          }
//          else if(user exists in DB)
//          {
          else { // TODO:: delete line
              SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).edit();

              editor.putString(USERNAME,userName);
              editor.putString(PASSWORD,password);
              editor.apply();

              setMainFragment();
          } // TODO:: delete line
//          else {
              // toast error
//              setLoginFragment();
//          }
    }

    public void Register(String name, String email, String password, String phone) {
//        TODO:: register to DB
        //if success:
        setLoginFragment();
        //toast "please login"
//        else
//        {
        //     toast "please try again with 6-chars password and correct data"
        //      setSigninFragment()
//
    }

    public void setMainFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();

        if (savedUserFlag == false) {
            fragmentTransaction.replace(R.id.fragmentcon, mainFragment).addToBackStack(null).commit();
        }
        else {
            fragmentTransaction.replace(R.id.fragmentcon, mainFragment).commit();
        }
    }

    public void setAppointmentsMainFragment() {
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

    public void setSelectAppointmentsFragment()
    {
        fragmentTransaction = fragmentManager.beginTransaction();
        SelectAppointmentsFragment selectAppointmentsFragment = new SelectAppointmentsFragment();
        fragmentTransaction.replace(R.id.fragmentcon, selectAppointmentsFragment).addToBackStack(null).commit();
    }

    public void setAdminFragment()
    {
        fragmentTransaction = fragmentManager.beginTransaction();
        AdminFragment adminFragment = new AdminFragment();
        fragmentTransaction.replace(R.id.fragmentcon, adminFragment).addToBackStack(null).commit();
    }
}