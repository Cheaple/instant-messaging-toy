package com.example.im.listener;

import com.example.im.bean.AccountInfo;

public interface OnLoginListener {
    void loginSuccess(AccountInfo account);
    void loginFailed(String e);
}
