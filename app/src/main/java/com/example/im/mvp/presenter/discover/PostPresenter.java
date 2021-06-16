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

    public PostPresenter(IPostContract.View view, Context context) {
        this.context = context;
        this.mModel = new PostModel();
        this.mView = view;
    }

    @Override
    public void postMoment() {
        ArrayList<Media> selected = mView.getSelected();
        boolean ifImageSelected = false;
        boolean ifVideoSelected = false;

        // 判断所选媒体是图片还是媒体
        for (int i = 0; i < selected.size(); ++i) {
            if (selected.get(i).mediaType == 1)  // 如果该媒体为图片
                ifImageSelected = true;
            else
                ifVideoSelected = true;
        }


        String text = mView.getText();  // 动态文本
        if (ifImageSelected && ifVideoSelected) {
            postFailure("不能同时上传图片和视频！");
        }
        else if (ifImageSelected) {

        }
        else {

        }
    }

    public void postSuccess() {
        mView.showText("发布成功");
    }

    public void postFailure(String error) {
        mView.showText(error);
    }
}
