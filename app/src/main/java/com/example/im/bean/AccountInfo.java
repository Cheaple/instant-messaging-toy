package com.example.im.bean;

import android.content.Context;
import android.content.SharedPreferences;

public class AccountInfo {
    private String id;
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
    public void saveUserInfo(Context context, String id, String password) {
        SharedPreferences sp = context.getSharedPreferences("AccountInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ID", id);
        editor.putString("Password", password);
        editor.commit();
    }

    // 检查是否已登录
    public boolean ifLoggedIn(Context context) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        this.id = sp.getString("USER_NAME", "");
        this.password = sp.getString("PASSWORD", "");
        if (!"".equals(this.id))
            return true;
        else
            return false;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public void setAccount(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
