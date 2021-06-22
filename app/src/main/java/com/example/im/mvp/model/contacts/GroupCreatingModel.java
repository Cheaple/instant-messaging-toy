package com.example.im.mvp.model.contacts;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.example.im.R;
import com.example.im.bean.contacts.Contact;
import com.example.im.listener.HttpCallbackListener;
import com.example.im.mvp.contract.contacts.IGroupCreatingContract;
import com.example.im.mvp.presenter.contacts.ContactsPresenter;
import com.example.im.mvp.presenter.contacts.GroupCreatingPresenter;
import com.example.im.util.HttpUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GroupCreatingModel implements IGroupCreatingContract.Model {
    private static final int LOAD_SUCCESS = 1;
    private static final int LOAD_FAILURE = 2;
    private static final int CREATE_SUCCESS = 3;
    private static final int CREATE_FAILURE = 4;
    private static final int INVITE_SUCCESS = 5;
    private static final int INVITE_FAILURE = 6;

    private GroupCreatingModel.MyHandler mHandler;
    public GroupCreatingModel(GroupCreatingPresenter presenter) {
        mHandler = new GroupCreatingModel.MyHandler(presenter);
    }

    private static class MyHandler extends Handler {
        private WeakReference<GroupCreatingPresenter> mWeakReference;

        public int invitedCnt = 0;
        private int invitedTotal;

        public MyHandler(GroupCreatingPresenter presenter) {
            mWeakReference = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            GroupCreatingPresenter mPresenter = mWeakReference.get();
            switch (msg.what) {
                case LOAD_SUCCESS:
                    mPresenter.loadSuccess((LinkedList<Contact>) msg.obj);
                    break;
                case LOAD_FAILURE:
                    mPresenter.loadFailure(msg.obj.toString());
                    break;
                case CREATE_SUCCESS:
                    mPresenter.createSuccess(msg.obj.toString());
                    break;
                case CREATE_FAILURE:
                    mPresenter.createFailure(msg.obj.toString());
                case INVITE_SUCCESS:
                    if (++invitedCnt >= invitedTotal)  // 选中的全部好友都邀请完毕
                        mPresenter.inviteSuccess();
                    break;
                case INVITE_FAILURE:
                    mPresenter.inviteFailure(msg.obj.toString());
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

    @Override
    public void createGroup(ArrayList<String> selectedContacts) {
        mHandler.invitedTotal = selectedContacts.size();
        try {
            // 构建http请求的body
            JSONObject body = new JSONObject();
            JSONArray memberList = new JSONArray(selectedContacts);
            body.put("groupType", "GROUP_CHAT");  // 会话类型：群聊
            body.put("memberIdList", memberList);

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
                    msg.what = CREATE_FAILURE;
                    msg.obj = e.toString();
                    mHandler.sendMessage(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inviteContacts(String groupID, ArrayList<String> selectedContacts) {
        for (int i = 0; i < selectedContacts.size(); ++i) {
            try {
                String url = "http://8.140.133.34:7200/chat/inviteUser?" + "groupId=" + groupID
                        + "&invitedUserId=" + selectedContacts.get(i);
                HttpUtil.sendHttpRequest(url, null, false, new HttpCallbackListener() {  // 发起http请求
                    @Override
                    public void onSuccess(String response) {  // http请求成功
                        Message msg = new Message();
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            if (jsonObject.getBoolean("success")) { // 创建成功
                                msg.what = INVITE_SUCCESS;
                                msg.obj = jsonObject.getString("chatGroupId");
                            }
                            else {  // 创建失败
                                msg.what = INVITE_FAILURE;
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
                        msg.what = INVITE_FAILURE;
                        msg.obj = e.toString();
                        mHandler.sendMessage(msg);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
