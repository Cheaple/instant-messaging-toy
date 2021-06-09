package com.example.im.mvp.model;

import com.example.im.listener.OnLoginListener;
import com.example.im.mvp.contract.base.ISignUpContract;

public class SignUpModel implements ISignUpContract.Model {
    @Override
    public void login(String id, String password, OnLoginListener listener) {
        // TODO: 注册
        if (true) {
            // listener.loginSuccess();
        }
        else
            listener.loginFailed();
    }
}
