package com.example.im.mvp.model.discover;

import android.os.Handler;
import android.os.Message;

import com.example.im.R;
import com.example.im.bean.chats.Chat;
import com.example.im.bean.discover.Discover;
import com.example.im.listener.HttpCallbackListener;
import com.example.im.mvp.contract.discover.IDiscoverContract;
import com.example.im.mvp.model.chats.ChatsModel;
import com.example.im.mvp.presenter.chats.ChatsPresenter;
import com.example.im.mvp.presenter.discover.DiscoverPresenter;
import com.example.im.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class DiscoverModel implements IDiscoverContract.Model {
    private static final int FAILURE = 100;
    private static final int LOAD_SUCCESS = 1;
    private static final int GIVE_SUCCESS = 2;
    private static final int CANCEL_SUCCESS = 3;
    private static final int COMMENT_SUCCESS = 4;

    private DiscoverModel.MyHandler mHandler;
    public DiscoverModel(DiscoverPresenter presenter) {
        mHandler = new DiscoverModel.MyHandler(presenter);
    }

    private static class MyHandler extends Handler {
        private WeakReference<DiscoverPresenter> mWeakReference;

        public MyHandler(DiscoverPresenter presenter) {
            mWeakReference = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            DiscoverPresenter mPresenter = mWeakReference.get();
            switch (msg.what) {
                case LOAD_SUCCESS:
                    mPresenter.loadSuccess((LinkedList<Discover>) msg.obj);
                    break;
                case GIVE_SUCCESS:
                    mPresenter.giveSuccess();
                    break;
                case COMMENT_SUCCESS:
                    mPresenter.commentSuccess();
                    break;
                case FAILURE:
                    mPresenter.discoverFailure(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void loadMomentList() {
        try {
            String url = "http://8.140.133.34:7200/moment/view/contacts";
            HttpUtil.sendHttpRequest(url, null, false, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success")) { // 加载动态列表成功

                            LinkedList<Discover> momentList = new LinkedList<>();
                            JSONArray momentJsonArray = jsonObject.getJSONArray("moments");
                            for (int i = 0; i < momentJsonArray.length(); ++i) {
                                String momentString = momentJsonArray.getString(i);
                                Gson gson = new Gson();
                                java.lang.reflect.Type type = new TypeToken<Discover>() {}.getType();
                                Discover moment = gson.fromJson(momentString, type);
                                momentList.add(moment);
                                System.out.println(moment.toString());
                            }

                            // 对动态按照发布时间进行排序
                            Collections.sort(momentList, new Comparator<Discover>() {
                                @Override
                                public int compare(Discover d1, Discover d2) {
                                    if (d1.getTime().compareTo(d2.getTime()) > 0) return -1;
                                    return 1;
                                }
                            });

                            msg.what = LOAD_SUCCESS;
                            msg.obj = momentList;
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
    public void giveLike(String momentId) {
        try {
            String url = "http://8.140.133.34:7200/moment/thumb" + "?id=" + momentId;
            HttpUtil.sendHttpRequest(url, null, false, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success")) { // 点赞成功
                            msg.what = GIVE_SUCCESS;
                        }
                        else {  // 点赞失败
                            //msg.what = FAILURE;
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
                    //msg.what = FAILURE;
                    msg.obj = e.toString();
                    mHandler.sendMessage(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelLike(String momentId) {}

    @Override
    public void makeComment(String momentId, String content) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", momentId);
        params.put("text", content);
        try {
            String url = HttpUtil.getUrlWithParams("http://8.140.133.34:7200/moment/reply", params);
            HttpUtil.sendHttpRequest(url, null, false, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success")) { // 评论成功
                            msg.what = COMMENT_SUCCESS;
                        }
                        else {  // 评论失败
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
