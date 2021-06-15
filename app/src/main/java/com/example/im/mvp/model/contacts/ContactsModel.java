package com.example.im.mvp.model.contacts;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.example.im.R;
import com.example.im.bean.contacts.Contact;
import com.example.im.listener.HttpCallbackListener;
import com.example.im.mvp.contract.contacts.IContactsContract;
import com.example.im.mvp.presenter.contacts.ContactsPresenter;
import com.example.im.mvp.presenter.contacts.InfoPresenter;
import com.example.im.util.HttpUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ContactsModel implements IContactsContract.Model {
    private static final int LOAD_SUCCESS = 1;
    private static final int LOAD_FAILURE = 2;

    private ContactsModel.MyHandler mHandler;
    public ContactsModel(ContactsPresenter presenter) {
        mHandler = new ContactsModel.MyHandler(presenter);
    }

    private static class MyHandler extends Handler {
        private WeakReference<ContactsPresenter> mWeakReference;

        public MyHandler(ContactsPresenter presenter) {
            mWeakReference = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ContactsPresenter mPresenter = mWeakReference.get();
            switch (msg.what) {
                case LOAD_SUCCESS:
                    //mPresenter.loadSuccess((LinkedList<Contact>) msg.obj);
                    break;
                case LOAD_FAILURE:
                    mPresenter.loadFailure(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void loadContactList() {
        try {
            String url = "http://8.140.133.34:7200/user/view";
            HttpUtil.sendHttpRequest(url, null, false, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success")) { // 加载好友列表成功
                            msg.what = LOAD_SUCCESS;
                            Gson gson = new Gson();
                            Contact[] contacts = gson.fromJson(jsonObject.getString("contacts"), Contact[].class);
                            msg.obj = new LinkedList<>(Arrays.asList(contacts));
                            // TODO: 获取联系人头像
                        }
                        else {  // 加载失败
                            msg.what = LOAD_FAILURE;
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
                    msg.what = LOAD_FAILURE;
                    msg.obj = e.toString();
                    mHandler.sendMessage(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
