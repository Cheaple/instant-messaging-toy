package com.example.im.mvp.model.base;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.im.listener.HttpCallbackListener;
import com.example.im.listener.OnLoginListener;
import com.example.im.mvp.contract.base.ISignInContract;
import com.example.im.util.HttpUtil;

import java.util.HashMap;

public class SignInModel implements ISignInContract.Model {
    //用于处理消息的Handler

    @Override
    public void login(String username, String password, OnLoginListener listener) {
        // TODO: 登录

        //构造HashMap
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);
        try {
            //构造完整URL
            String URL = HttpUtil.getURLWithParams("http://8.140.133.34:7200/user/login", params);
            //发送请求
            HttpUtil.sendHttpRequest(URL, new HttpCallbackListener() {
                @Override
                public void onSuccess(String response) {
                }

                @Override
                public void onFailure(Exception e) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (true) {
            // listener.loginSuccess();
        }
        else
            listener.loginFailed();
    }
}
