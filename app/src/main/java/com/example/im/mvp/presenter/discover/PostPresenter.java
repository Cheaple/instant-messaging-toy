package com.example.im.mvp.presenter.discover;

import android.content.Context;

import com.dmcbig.mediapicker.entity.Media;
import com.example.im.bean.discover.Discover;
import com.example.im.mvp.contract.discover.IDiscoverContract;
import com.example.im.mvp.contract.discover.IPostContract;
import com.example.im.mvp.model.discover.DiscoverModel;
import com.example.im.mvp.model.discover.PostModel;

import java.util.ArrayList;
import java.util.LinkedList;

public class PostPresenter implements IPostContract.Presenter {
    private Context context;

    IPostContract.Model mModel;
    IPostContract.View mView;

    String momentType;

    public PostPresenter(IPostContract.View view, Context context) {
        this.context = context;
        this.mModel = new PostModel(this);
        this.mView = view;
    }

    @Override
    public void postMoment() {
        ArrayList<Media> selected = mView.getSelected();
        ArrayList<String> files = new ArrayList<>();
        boolean ifImageSelected = false;
        boolean ifVideoSelected = false;

        // 判断所选媒体是图片还是媒体
        for (int i = 0; i < selected.size(); ++i) {
            if (selected.get(i).mediaType == 1)  // 如果该媒体为图片
                ifImageSelected = true;
            else
                ifVideoSelected = true;
            files.add(selected.get(i).path);
        }


        String text = mView.getText();  // 动态文本
        if (ifImageSelected && ifVideoSelected) {
            mView.showText("不能同时发布图片和视频！");
        }
        else if (ifImageSelected) {
            if ("".equals(text)) momentType = "PICTURE";
            else momentType = "PICTURE_TEXT";
            mModel.upload(momentType, files);
        }
        else if (ifVideoSelected) {
            momentType = "VIDEO";
            mModel.upload(momentType, files);
        }
        else if (!"".equals(text)){
            momentType = "TEXT";
            mModel.postMoment(momentType, text, null);
        }
    }

    public void post(String text, ArrayList<String> files) {
        mModel.postMoment(momentType, text, files);
    }

    public void uploadSuccess(ArrayList<String> files) {
        String text = mView.getText();
        post(text, files);
    }

    public void postSuccess() {
        mView.showText("发布成功");
        mView.gotoMainActivity();
    }

    public void postFailure(String error) {
        mView.showText(error);
    }
}
