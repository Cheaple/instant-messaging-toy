package com.example.im.mvp.model.base;

import android.os.Handler;
import android.os.Message;

import com.example.im.bean.contacts.Contact;
import com.example.im.listener.HttpCallbackListener;
import com.example.im.listener.OnLoginListener;
import com.example.im.mvp.contract.base.ISignUpContract;
import com.example.im.mvp.presenter.base.SignInPresenter;
import com.example.im.mvp.presenter.base.SignUpPresenter;
import com.example.im.util.HttpUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class SignUpModel implements ISignUpContract.Model {
    private static final int LOGIN_FAILURE = 100;
    private static final int REGISTER_SUCCESS = 1;
    private static final int LOGIN_SUCCESS = 2;
    private static final int LOAD_SUCCESS = 3;

    private SignUpModel.MyHandler mHandler;
    public SignUpModel(SignUpPresenter presenter) {
        mHandler = new SignUpModel.MyHandler(presenter);
    }

    private static class MyHandler extends Handler {
        private WeakReference<SignUpPresenter> mWeakReference;

        public MyHandler(SignUpPresenter presenter) {
            mWeakReference = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SignUpPresenter mPresenter = mWeakReference.get();
            switch (msg.what) {
                case REGISTER_SUCCESS:
                    mPresenter.registerSuccess();
                    break;
                case LOGIN_SUCCESS:
                    mPresenter.loginSuccess();
                    break;
                case LOAD_SUCCESS:
                    mPresenter.loadSuccess((Contact) msg.obj);
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
    public void register(String username, String password) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);
        try {
            String url = HttpUtil.getUrlWithParams("http://8.140.133.34:7200/user/register", params);
            HttpUtil.sendHttpRequest(url, null, true, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success"))  // 注册成功
                            msg.what = REGISTER_SUCCESS;
                        else {  // 注册失败
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
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success"))  // 登陆成功
                            msg.what = LOGIN_SUCCESS;
                        else {  // 登陆失败
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

    @Override
    public void loadInfo(String username) {
        try {
            String url = "http://8.140.133.34:7200/user/search" + "?search=" + username;
            HttpUtil.sendHttpRequest(url, null, false, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    System.out.println(response.toString());
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success")) { // 查找成功
                            msg.what = LOAD_SUCCESS;
                            JSONObject userJsonObject = jsonObject.getJSONObject("user");
                            String id = userJsonObject.getString("id");
                            String avatar = userJsonObject.getString("avatar");
                            String nickname = userJsonObject.getString("nickname");
                            String username = userJsonObject.getString("username");
                            msg.obj = new Contact(id, avatar, nickname, username);
                        }
                        else {  // 查找失败
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
