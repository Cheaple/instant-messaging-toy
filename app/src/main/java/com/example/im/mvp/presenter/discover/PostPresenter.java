package com.example.im.mvp.presenter.discover;

import android.content.Context;

import com.example.im.bean.discover.Discover;
import com.example.im.mvp.contract.discover.IDiscoverContract;
import com.example.im.mvp.contract.discover.IPostContract;
import com.example.im.mvp.model.discover.DiscoverModel;
import com.example.im.mvp.model.discover.PostModel;

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

    }
}
