package com.example.im.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.im.R;
import com.example.im.fragment.SignInFragment;
import com.example.im.fragment.SignUpFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.bottomNavigationView)
    public BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Fragment signInFragment = new SignInFragment();
        Fragment signUpFragment = new SignUpFragment();

        setCurrentFragment(signInFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.sign_in:
                            setCurrentFragment(signInFragment);
                            return true;
                        case R.id.sign_up:
                            setCurrentFragment(signUpFragment);
                            return true;
                    }
                    return false;
                }
        );
    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment_login, fragment).commit();
    }
}