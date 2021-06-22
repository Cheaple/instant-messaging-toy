package com.example.im.mvp.model.chats;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;

import com.example.im.R;
import com.example.im.bean.AccountInfo;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ChattingModel implements IChattingContract.Model {
    private static final int FAILURE = 100;
    private static final int UPLOAD_SUCCESS = 101;
    private static final int LOAD_MSG_SUCCESS = 1;
    private static final int LOAD_MEMBER_SUCCESS = 2;
    private static final int SEND_SUCCESS = 3;
    private static final int CHECK_SUCCESS = 4;

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
                case UPLOAD_SUCCESS:
                    mPresenter.uploadSuccess((String) msg.obj);  // 传回文件名
                    break;
                case LOAD_MSG_SUCCESS:
                    mPresenter.loadSuccess((LinkedList<Msg>) msg.obj);
                    break;
                case LOAD_MEMBER_SUCCESS:
                    mPresenter.loadSuccess((ArrayList<Contact>) msg.obj);
                    break;
                case SEND_SUCCESS:
                    mPresenter.sendSuccess();
                    break;
                case CHECK_SUCCESS:
                    if (msg.arg1 == 1)  // 该用户为当前用户的好友
                        mPresenter.checkSuccess((Contact) msg.obj, true);
                    else
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
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.has("messages")) {  // 若有messages字段，则查找成功，但该字段可能为空
                            msg.what = LOAD_MSG_SUCCESS;
                            LinkedList<Msg> msgList = new LinkedList<>();

                            if (jsonObject.getBoolean("success")) {  // 若 messages字段非空，则解析之
                                JSONArray msgJsonArray = jsonObject.getJSONArray("messages");
                                for (int i = 0; i < msgJsonArray.length(); ++i) {
                                    JSONObject msgJsonObject = msgJsonArray.getJSONObject(i);
                                    String senderId = msgJsonObject.getString("senderId");
                                    if (!msgJsonObject.has("attachmentType")) {
                                        String text = msgJsonObject.getString("text");
                                        msgList.add(new Msg(senderId, Msg.TYPE_MSG, text));
                                    }
                                    else {
                                        String type = msgJsonObject.getString("attachmentType");
                                        String content = msgJsonObject.getString("attachmentName");
                                        if ("PICTURE".equals(type)) {
                                            msgList.add(new Msg(senderId, Msg.TYPE_PICTURE, content));
                                        }
                                        else if ("VIDEO".equals(type)) {
                                            msgList.add(new Msg(senderId, Msg.TYPE_VIDEO, content));
                                        }
                                    }
                                }
                            }
                            msg.obj = new LinkedList<>(msgList);
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
    public void loadMemberInfo(ArrayList<String> members) {
        try {
            // 构建http请求的body
            JSONObject body = new JSONObject();
            JSONArray memberList = new JSONArray(members);
            body.put("users", memberList);  // 会话类型：群聊

            String url = "http://8.140.133.34:7200/user/searchUsers";
            HttpUtil.sendHttpRequest(url, body, false, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success")) {  // 加载群成员成功
                            msg.what = LOAD_MEMBER_SUCCESS;
                            Gson gson = new Gson();
                            Contact[] contacts = gson.fromJson(jsonObject.getString("users"), Contact[].class);
                            msg.obj = new ArrayList(Arrays.asList(contacts));
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
    public void clearHistory(String username) {
        // todo: 删除指定消息
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
                            if (jsonObject.getBoolean("isContact")) msg.arg1 = 1;
                            else msg.arg1 = 0;
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
    public void sendLocation(String id) {
        // TODO：发送地理位置
    }

    @Override
    public void upload(String fileType, String file) {
        try {
            String url = "http://8.140.133.34:7200/upload";
            HttpUtil.uploadFile(url, file, fileType, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success")) { // 上传成功
                            msg.what = UPLOAD_SUCCESS;
                            msg.obj = jsonObject.getString("filename");
                        }
                        else {  // 上传失败
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
    public void send(String chatID, String content, int msgType) {
        try {
            String url = "http://8.140.133.34:7200/chat/addMessage" + "?groupId=" + chatID;
            switch (msgType) {
                case Msg.TYPE_MSG:
                    url = url + "&text=" + content;
                    break;
                case Msg.TYPE_PICTURE:
                    url = url + "&attachmentType=PICTURE" + "&attachmentName=" + content + "&text=" + "null";
                    break;
                case Msg.TYPE_VIDEO:
                    url = url + "&attachmentType=VIDEO" + "&attachmentName=" + content + "&text=" + "null";
                    break;
            }
            HttpUtil.sendHttpRequest(url, null, false, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success")) { // 发送成功
                            msg.what = SEND_SUCCESS;
                        }
                        else {  // 发送失败
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
}
