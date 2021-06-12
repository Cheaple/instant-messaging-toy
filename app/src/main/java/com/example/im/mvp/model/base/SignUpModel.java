package com.example.im.mvp.model.base;

import com.example.im.listener.HttpCallbackListener;
import com.example.im.listener.OnLoginListener;
import com.example.im.mvp.contract.base.ISignUpContract;
import com.example.im.util.HttpUtil;

import java.util.HashMap;

public class SignUpModel implements ISignUpContract.Model {
    @Override
    public void login(String username, String password, OnLoginListener listener) {
        // TODO: 注册

        if (true) {
            // listener.loginSuccess();
        }
        else
            listener.loginFailed();
    }
}
