package com.example.im.mvp.model.chats;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.example.im.R;
import com.example.im.bean.chats.Chat;
import com.example.im.bean.chats.Msg;
import com.example.im.bean.contacts.Contact;
import com.example.im.listener.HttpCallbackListener;
import com.example.im.mvp.contract.chats.IChattingContract;
import com.example.im.mvp.presenter.chats.ChatsPresenter;
import com.example.im.mvp.presenter.chats.ChattingPresenter;
import com.example.im.util.HttpUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ChattingModel implements IChattingContract.Model {
    private static final int FAILURE = 100;
    private static final int LOAD_SUCCESS = 1;
    private static final int SEND_SUCCESS = 2;
    private static final int CHECK_SUCCESS = 3;

    private ChattingModel.MyHandler mHandler;
    public ChattingModel(ChattingPresenter presenter) {
        mHandler = new ChattingModel.MyHandler(presenter);
    }

    private static class MyHandler extends Handler {
        private WeakReference<ChattingPresenter> mWeakReference;

        public MyHandler(ChattingPresenter presenter) {
            mWeakReference = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ChattingPresenter mPresenter = mWeakReference.get();
            switch (msg.what) {
                case LOAD_SUCCESS:
                    mPresenter.loadSuccess((LinkedList<Msg>) msg.obj);
                    break;
                case SEND_SUCCESS:
                    mPresenter.sendSuccess();
                    break;
                case CHECK_SUCCESS:
                    // TODO: 判断该用户是否为当前用户的好友
                    mPresenter.checkSuccess((Contact) msg.obj, false);
                    break;
                case FAILURE:
                    mPresenter.chattingFailure(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void loadMsgList(String chatId) {
        try {
            String url = "http://8.140.133.34:7200/chat/getMessage" + "?groupId=" + chatId;
            HttpUtil.sendHttpRequest(url, null, false, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        msg.what = LOAD_SUCCESS;
                        LinkedList<Msg> msgList = new LinkedList<>();

                        // TODO: 获取历史消息
                        JSONArray msgJsonArray = new JSONArray(response.toString());
                        for (int i = 0; i < msgJsonArray.length(); ++i) {
                            JSONObject jsonObject = msgJsonArray.getJSONObject(i);
                            String text = jsonObject.getString("text");
                            String senderId = jsonObject.getString("senderId");
                            msgList.add(new Msg(senderId, Msg.TYPE_MSG, text));
                        }
                        msg.obj = new LinkedList<>(msgList);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    mHandler.sendMessage(msg);
                }

                @Override
                public void onFailure(Exception e) {  // http请求失败
                    Message msg = new Message();
                    msg.what = FAILURE;
                    msg.obj = e.toString();
                    mHandler.sendMessage(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void checkInfo(String username) {
        try {
            String url = "http://8.140.133.34:7200/user/search" + "?search=" + username;
            HttpUtil.sendHttpRequest(url, null, false, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success")) { // 修改成功
                            msg.what = CHECK_SUCCESS;
                            Gson gson = new Gson();
                            msg.obj = gson.fromJson(jsonObject.getString("user"), Contact.class);
                        }
                        else {  // 修改失败
                            msg.what = FAILURE;
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
                    msg.what = FAILURE;
                    msg.obj = e.toString();
                    mHandler.sendMessage(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendMsg(String chatId, String content) {
        try {
            String url = "http://8.140.133.34:7200/chat/addMessage" + "?groupId=" + chatId + "&text=" + content;
            HttpUtil.sendHttpRequest(url, null, false, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success")) { // 加载历史消息成功
                        msg.what = SEND_SUCCESS;
                        }
                        else {  // 加载失败
                            msg.what = FAILURE;
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
                    msg.what = FAILURE;
                    msg.obj = e.toString();
                    mHandler.sendMessage(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendPicture(String id, String path) {
        // TODO: 发送图片
    }

    @Override
    public void sendVideo(String id, String path) {
        // TODO：发送视频
    }

    @Override
    public void sendLocation(String id) {
        // TODO：发送
    }
}
