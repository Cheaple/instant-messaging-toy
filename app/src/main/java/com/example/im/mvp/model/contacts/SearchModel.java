package com.example.im.mvp.model.contacts;

import android.os.Handler;
import android.os.Message;

import com.example.im.bean.contacts.Contact;
import com.example.im.listener.HttpCallbackListener;
import com.example.im.mvp.contract.contacts.ISearchContract;
import com.example.im.mvp.presenter.contacts.SearchPresenter;
import com.example.im.util.HttpUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class SearchModel implements ISearchContract.Model{
    private static final int SEARCH_SUCCESS = 1;
    private static final int SEARCH_FAILURE = 2;

    private SearchModel.MyHandler mHandler;
    public SearchModel(SearchPresenter presenter) {
        mHandler = new SearchModel.MyHandler(presenter);
    }

    private static class MyHandler extends Handler {
        private WeakReference<SearchPresenter> mWeakReference;

        public MyHandler(SearchPresenter presenter) {
            mWeakReference = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SearchPresenter mPresenter = mWeakReference.get();
            switch (msg.what) {
                case SEARCH_SUCCESS:
                    // TODO: 判断该用户是否为当前用户的好友
                    mPresenter.searchSuccess((Contact) msg.obj, false);
                    break;
                case SEARCH_FAILURE:
                    mPresenter.searchFailure(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void loadInvitationList() {}

    @Override
    public void searchUser(String target) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("search", target);
        try {
            String url = HttpUtil.getUrlWithParams("http://8.140.133.34:7200/user/search", params);
            HttpUtil.sendHttpRequest(url, null, false, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success")) { // 修改成功
                            msg.what = SEARCH_SUCCESS;
                            Gson gson = new Gson();
                            msg.obj = gson.fromJson(jsonObject.getString("user"), Contact.class);
                            // TODO: 获取联系人头像
                        }
                        else {  // 修改失败
                            msg.what = SEARCH_FAILURE;
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
                    msg.what = SEARCH_FAILURE;
                    msg.obj = e.toString();
                    mHandler.sendMessage(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void accept(String username) {}

    @Override
    public void refuse(String username) {}
}
