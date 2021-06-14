package com.example.im.mvp.model.base;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.im.listener.HttpCallbackListener;
import com.example.im.listener.OnLoginListener;
import com.example.im.mvp.contract.base.ISignInContract;
import com.example.im.mvp.presenter.base.SignInPresenter;
import com.example.im.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import io.reactivex.Single;

public class SignInModel implements ISignInContract.Model {
    private static final int LOGIN_SUCCESS = 0;
    private static final int LOGIN_FAILURE = 1;

    private MyHandler mHandler;
    public SignInModel(SignInPresenter presenter) {
        mHandler = new MyHandler(presenter);
    }

    private static class MyHandler extends Handler {
        private WeakReference<SignInPresenter> mWeakReference;

        public MyHandler(SignInPresenter presenter) {
            mWeakReference = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SignInPresenter mPresenter = mWeakReference.get();
            switch (msg.what) {
                case LOGIN_SUCCESS:
                    mPresenter.loginSuccess();
                    break;
                case LOGIN_FAILURE:
                    mPresenter.loginFailure(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void login(String username, String password) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);
        try {
            String url = HttpUtil.getUrlWithParams("http://8.140.133.34:7200/user/login", params);
            HttpUtil.sendHttpRequest(url, null, true, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    System.out.println(response.toString());
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success"))  // 登录成功
                            msg.what = LOGIN_SUCCESS;
                        else {  // 登录失败
                            msg.what = LOGIN_FAILURE;
                            msg.obj = jsonObject.getString("msg");  // 失败原因
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    mHandler.sendMessage(msg);
                }

                @Override
                public void onFailure(Exception e) {  // http请求失败
                    Message msg = new Message();
                    msg.what = LOGIN_FAILURE;
                    msg.obj = e.toString();
                    mHandler.sendMessage(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
