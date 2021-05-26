package com.example.haircutscheduling.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.classes.FirstEntry;
import com.example.haircutscheduling.classes.User;
import com.example.haircutscheduling.fragments.AdminFragment;
import com.example.haircutscheduling.fragments.AppointmentsMainFragment;
import com.example.haircutscheduling.fragments.BookedAppoitmentsFragment;
import com.example.haircutscheduling.fragments.EditDaysOffFragment;
import com.example.haircutscheduling.fragments.EditUpdatesBoardFragment;
import com.example.haircutscheduling.fragments.LoginFragment;
import com.example.haircutscheduling.fragments.MainFragment;
import com.example.haircutscheduling.fragments.SelectAppointmentsFragment;
import com.example.haircutscheduling.fragments.SigninFragment;
import com.example.haircutscheduling.fragments.UpdatesBoardFragment;
import com.example.haircutscheduling.fragments.UserHistoryFragment;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

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

        if (FirstEntry.flag)
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
        if (fragmentManager.findFragmentById(R.id.fragmentcon).getClass() == MainFragment.class ||
                fragmentManager.findFragmentById(R.id.fragmentcon).getClass() == AdminFragment.class)
        {
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

          if (userName.equals("admin") && password.equals("admin")) {
              setFragment(new AdminFragment());
          }
          else // TODO: switch to -> else if(user exists in DB)
          {
              // TODO:: Login to DB...

              SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).edit();

              editor.putString(USERNAME,userName);
              editor.putString(PASSWORD,password);
              editor.apply();

              setMainFragment();
          }
//          else { TODO: add this
//               Toast.makeText(this, "Wrong email or password!\nPlease try again.", Toast.LENGTH_LONG).show();
//          }
    }

    public void Register(String name, String email, String password, String phone) {

        // TODO:: register to DB

        User user = new User(name, email, password, phone);

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

        if (!savedUserFlag) {
            fragmentTransaction.replace(R.id.fragmentcon, mainFragment).addToBackStack(null).commit();
        }
        else {
            fragmentTransaction.replace(R.id.fragmentcon, mainFragment).commit();
        }
    }

    public void setFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentcon, fragment).addToBackStack(null).commit();
    }

    public void setSelectAppointmentsFragment(String hairStyle)
    {
        // TODO:: use parameter hairStyle for appointment
        fragmentTransaction = fragmentManager.beginTransaction();
        SelectAppointmentsFragment selectAppointmentsFragment = new SelectAppointmentsFragment();
        fragmentTransaction.replace(R.id.fragmentcon, selectAppointmentsFragment).addToBackStack(null).commit();
    }

    public void addDayOff(String day, String month, String year)
    {
        String dayOff = day + "/" + month + "/" + year;
        Toast.makeText(this,"dayOff updated successfuly on " + dayOff,Toast.LENGTH_LONG).show();
        // TODO:: update day not available on 'selectAppointment'
    }

    public void cancelDayOff(String day, String month, String year)
    {
        String dayOff = day + "/" + month + "/" + year;
        Toast.makeText(this,"dayOff canceled successfuly on " + dayOff,Toast.LENGTH_LONG).show();
        // TODO:: update day available on 'selectAppointment'
    }

    public void addToUpdatesBoard(String update)
    {
        if (update.isEmpty())
        {
            Toast.makeText(this, "Please write your update", Toast.LENGTH_SHORT).show();
        }
        else {
            // TODO:: add update to DB -> update + the date
            Toast.makeText(this, "Update added to board", Toast.LENGTH_SHORT).show();
        }
    }
}