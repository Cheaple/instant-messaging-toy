package com.example.im.mvp.model.settings;

import android.os.Handler;
import android.os.Message;

import com.example.im.listener.HttpCallbackListener;
import com.example.im.mvp.contract.settings.ISettingsContract;
import com.example.im.mvp.model.base.SignInModel;
import com.example.im.mvp.presenter.base.SignInPresenter;
import com.example.im.mvp.presenter.settings.SettingsPresenter;
import com.example.im.util.HttpUtil;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class SettingsModel implements ISettingsContract.Model {
    private static final int CHANGE_SUCCESS = 0;
    private static final int CHANGE_FAILURE = 1;

    private SettingsModel.MyHandler mHandler;
    public SettingsModel(SettingsPresenter presenter) {
        mHandler = new SettingsModel.MyHandler(presenter);
    }

    private static class MyHandler extends Handler {
        private WeakReference<SettingsPresenter> mWeakReference;

        public MyHandler(SettingsPresenter presenter) {
            mWeakReference = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SettingsPresenter mPresenter = mWeakReference.get();
            switch (msg.what) {
                case CHANGE_SUCCESS:
                    mPresenter.changeSuccess();
                    break;
                case CHANGE_FAILURE:
                    mPresenter.changeFailure(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void changeAvatar() {
        // TODO: 更新头像
    }

    @Override
    public void changeNickname(String username, String nickname) {
        change("nickname", "nickname", nickname);
    }

    @Override
    public void changeUsername(String username, String new_username) {
        change("username", "name", new_username);
    }

    @Override
    public void changeRegion(String username, String region) {}

    @Override
    public void changePassword(String username, String password) {
        change("password", "password", password);
    }

    private void change(String operation, String paramName, String param) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(paramName, param);
        try {
            String url = HttpUtil.getUrlWithParams("http://8.140.133.34:7200/user/" + operation, params);
            HttpUtil.sendHttpRequest(url, null, false, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success"))  // 修改成功
                            msg.what = CHANGE_SUCCESS;
                        else {  // 修改失败
                            msg.what = CHANGE_FAILURE;
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
                    msg.what = CHANGE_FAILURE;
                    msg.obj = e.toString();
                    mHandler.sendMessage(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logout() {}
}
