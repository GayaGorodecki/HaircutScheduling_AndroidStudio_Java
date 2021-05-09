package com.example.haircutscheduling.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.haircutscheduling.R;
import com.example.haircutscheduling.fragments.LoginFragment;
import com.example.haircutscheduling.fragments.MainFragment;
import com.example.haircutscheduling.fragments.SigninFragment;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
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

        // if (TODO:: ... check if user email & password is in database...)
//        {
            setMainFragment();
//        }
    }

    public void setMainFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        fragmentTransaction.replace(R.id.fragmentcon, mainFragment).addToBackStack(null).commit();
    }
}