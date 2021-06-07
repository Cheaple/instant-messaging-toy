package com.example.im.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.im.R;
import com.example.im.fragment.ChatsFragment;
import com.example.im.fragment.ContactsFragment;
import com.example.im.fragment.DiscoverFragment;
import com.example.im.fragment.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.view.Menu;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomNavigationView)
    public BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Fragment chatsFragment = new ChatsFragment();
        Fragment contactsFragment = new ContactsFragment();
        Fragment discoverFragment = new DiscoverFragment();
        Fragment settingsFragment = new SettingsFragment();

        setCurrentFragment(chatsFragment); // 初始的Fragment为chatsFragment

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.chats:
                            setCurrentFragment(chatsFragment);
                            return true;
                        case R.id.contacts:
                            setCurrentFragment(contactsFragment);
                            return true;
                        case R.id.discover:
                            setCurrentFragment(discoverFragment);
                            return true;
                        case R.id.settings:
                            setCurrentFragment(settingsFragment);
                            return true;
                    }
                    return false;
                }
        );
    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }
}