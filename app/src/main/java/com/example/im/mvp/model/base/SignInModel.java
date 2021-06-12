package com.example.im.mvp.model.base;

import com.example.im.listener.OnLoginListener;
import com.example.im.mvp.contract.base.ISignInContract;

public class SignInModel implements ISignInContract.Model {
    @Override
    public void login(String username, String password, OnLoginListener listener) {
        // TODO: 登录
        if (true) {
            // listener.loginSuccess();
        }
        else
            listener.loginFailed();
    }
}
