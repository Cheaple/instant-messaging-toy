package com.example.im.bean;

import android.content.Context;
import android.content.SharedPreferences;

public class AccountInfo {
    private String username;
    private String password;

    private static AccountInfo instance;

    private AccountInfo() { }

    public static AccountInfo getInstance() {
        if (instance == null) {
            instance = new AccountInfo();
        }
        return instance;
    }

    // 保存自动登录的用户信息
    public void saveAccountInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("AccountInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Username", username);
        editor.putString("Password", password);
        editor.commit();
    }

    // 清除自动登录的用户信息
    public void clearAccountInfo(Context context) {
        setAccount("", "");
        saveAccountInfo(context);
    }

    // 检查是否已登录
    public boolean ifLoggedIn(Context context) {
        SharedPreferences sp = context.getSharedPreferences("AccountInfo", Context.MODE_PRIVATE);
        this.username = sp.getString("Username", "");
        this.password = sp.getString("Password", "");
        if (!"".equals(this.username))
            return true;
        else
            return false;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setAccount(String id, String password) {
        this.username = id;
        this.password = password;
    }
}
