package com.example.im.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.im.R;
import com.example.im.bean.AccountInfo;


public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (AccountInfo.getInstance().ifLoggedIn(this)) {
            // 已登录：跳转至主界面
            Toast.makeText(this, "To Main", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            // 未登录：跳转至登录界面
            Toast.makeText(this, "To Login", Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent2);
        }
        finish();
    }
}
