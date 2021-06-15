package com.example.im.mvp.model.contacts;

import android.os.Handler;
import android.os.Message;

import com.example.im.bean.contacts.Contact;
import com.example.im.listener.HttpCallbackListener;
import com.example.im.mvp.contract.contacts.IInfoContract;
import com.example.im.mvp.presenter.contacts.InfoPresenter;
import com.example.im.mvp.presenter.contacts.SearchPresenter;
import com.example.im.util.HttpUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class InfoModel implements IInfoContract.Model {
    private static final int ADD_SUCCESS = 1;
    private static final int ADD_FAILURE = 2;
    private static final int DELETE_SUCCESS = 3;
    private static final int DELETE_FAILURE = 4;
    private static final int CREATE_SUCCESS = 5;
    private static final int CREATE_FAILURE = 6;
    private static final int CLEAR_SUCCESS = 7;
    private static final int CLEAR_FAILURE = 8;

    private InfoModel.MyHandler mHandler;
    public InfoModel(InfoPresenter presenter) {
        mHandler = new InfoModel.MyHandler(presenter);
    }

    private static class MyHandler extends Handler {
        private WeakReference<InfoPresenter> mWeakReference;

        public MyHandler(InfoPresenter presenter) {
            mWeakReference = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            InfoPresenter mPresenter = mWeakReference.get();
            switch (msg.what) {
                case ADD_SUCCESS:
                    mPresenter.addSuccess();
                    break;
                case ADD_FAILURE:
                    mPresenter.addFailure(msg.obj.toString());
                    break;
                case DELETE_SUCCESS:
                    mPresenter.deleteSuccess();
                    break;
                case DELETE_FAILURE:
                    mPresenter.deleteFailure(msg.obj.toString());
                    break;
                case CREATE_SUCCESS:
                    mPresenter.createSuccess(msg.obj.toString());
                    break;
                case CREATE_FAILURE:
                    mPresenter.createFailure(msg.obj.toString());
                    break;
                case CLEAR_SUCCESS:
                    mPresenter.clearSuccess();
                    break;
                case CLEAR_FAILURE:
                    mPresenter.clearFailure(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void delete(String username) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("delete", username);
        try {
            String url = HttpUtil.getUrlWithParams("http://8.140.133.34:7200/user/delete", params);
            HttpUtil.sendHttpRequest(url, null, false, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success")) { // 删除成功
                            msg.what = DELETE_SUCCESS;
                        }
                        else {  // 删除失败
                            msg.what = DELETE_FAILURE;
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
                    msg.what = ADD_FAILURE;
                    msg.obj = e.toString();
                    mHandler.sendMessage(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(String username) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("add", username);
        try {
            String url = HttpUtil.getUrlWithParams("http://8.140.133.34:7200/user/add", params);
            HttpUtil.sendHttpRequest(url, null, false, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        System.out.println(response.toString());
                        if (jsonObject.getBoolean("success")) { // 添加成功
                            msg.what = ADD_SUCCESS;
                        }
                        else {  // 添加失败
                            msg.what = ADD_FAILURE;
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
                    msg.what = ADD_FAILURE;
                    msg.obj = e.toString();
                    mHandler.sendMessage(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createChatting(String username, String target) {
        try {
            // 构建http请求的body
            JSONObject body = new JSONObject();
            JSONArray memberList = new JSONArray();
            memberList.put(username);
            memberList.put(target);
            body.put("groupType", "PRIVATE_CHAT");  // 会话类型：私人会话
            body.put("memberList", memberList);

            String url = "http://8.140.133.34:7200/chat/create";
            HttpUtil.sendHttpRequest(url, body, false, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.has("chatGroupId")) { // 创建成功
                            msg.what = CREATE_SUCCESS;
                            msg.obj = jsonObject.getString("chatGroupId");
                        }
                        else {  // 创建失败
                            msg.what = CREATE_FAILURE;
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
                    msg.what = ADD_FAILURE;
                    msg.obj = e.toString();
                    mHandler.sendMessage(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearChattingHistory(String username) {
        //TODO: 清空历史记录
   }
}
