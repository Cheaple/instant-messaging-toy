package com.example.im.mvp.model.discover;

import android.os.Handler;
import android.os.Message;

import com.example.im.bean.contacts.Contact;
import com.example.im.bean.discover.Discover;
import com.example.im.listener.HttpCallbackListener;
import com.example.im.mvp.contract.discover.IPostContract;
import com.example.im.mvp.presenter.discover.DiscoverPresenter;
import com.example.im.mvp.presenter.discover.PostPresenter;
import com.example.im.util.HttpUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;

public class PostModel implements IPostContract.Model {
    private static final int UPLOAD_SUCCESS = 1;
    private static final int POST_SUCCESS = 2;
    private static final int FAILURE = 100;

    private String momentType;
    private String text;

    private PostModel.MyHandler mHandler;
    public PostModel(PostPresenter presenter) {
        mHandler = new PostModel.MyHandler(presenter);
    }

    private static class MyHandler extends Handler {
        private WeakReference<PostPresenter> mWeakReference;

        public ArrayList<String> uploadedFileList = new ArrayList<>();
        public int fileCnt;

        public MyHandler(PostPresenter presenter) {
            mWeakReference = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PostPresenter mPresenter = mWeakReference.get();
            switch (msg.what) {
                case UPLOAD_SUCCESS:
                    uploadedFileList.add((String) msg.obj);
                    if (uploadedFileList.size() == fileCnt)  // 若所有文件都上传成功
                        mPresenter.uploadSuccess(uploadedFileList);  // 则发布动态
                case POST_SUCCESS:
                    mPresenter.postSuccess();
                    break;
                case FAILURE:
                    mPresenter.postFailure(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void postMoment(String momentType, String text, ArrayList<String> files) {
        try {
            JSONObject body = new JSONObject();
            String url = "http://8.140.133.34:7200/moment/publish";
            if (momentType == "TEXT") {
                url = url + "?momentType=" + "TEXT" + "&text=" + text;
            }
            else if (momentType == "VIDEO") {
                url = url + "?momentType=" + "VIDEO" + "&video=" + files.get(0);
            }
            else if (momentType == "PICTURE") {
                System.out.println(files.toString());
                JSONArray pictureList = new JSONArray(files);
                body.put("pictures", pictureList);  // 用body传输图片数组
                url = url + "?momentType=" + "PICTURE";
                body.put("momentType", "PICTURE");
            }
            else {
                System.out.println(files.toString());
                JSONArray pictureList = new JSONArray(files);
                body.put("pictures", pictureList);  // 用body传输图片数组
                url = url + "?momentType=" + "PICTURE_TEXT" + "&text=" + text;
                body.put("momentType", "PICTURE_TEXT");
            }
            HttpUtil.sendHttpRequest(url, body, false, new HttpCallbackListener() {  // 发起http请求
                @Override
                public void onSuccess(String response) {  // http请求成功
                    Message msg = new Message();
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        if (jsonObject.getBoolean("success")) { // 发布成功
                            msg.what = UPLOAD_SUCCESS;
                        }
                        else {  // 发布失败
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
    public void upload(String fileType, ArrayList<String> files) {
        if (fileType == "PICTURE") {
            mHandler.fileCnt = files.size();
            for (int i = 0; i < files.size(); ++i)
                upload("PICTURE", files.get(i));
        }
        else if (fileType == "VIDEO") {
            mHandler.fileCnt = 1;
            upload("VIDEO", files.get(0));
        }
    }

    private void upload(String fileType, String file) {
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
}
