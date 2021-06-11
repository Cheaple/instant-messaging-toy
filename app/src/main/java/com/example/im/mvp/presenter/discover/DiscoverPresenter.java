package com.example.im.mvp.presenter.discover;

import android.content.Context;

import com.example.im.bean.discover.Discover;
import com.example.im.mvp.contract.discover.IDiscoverContract;
import com.example.im.mvp.contract.settings.ISettingsContract;
import com.example.im.mvp.model.discover.DiscoverModel;
import com.example.im.mvp.model.settings.SettingsModel;

import java.util.LinkedList;

public class DiscoverPresenter implements IDiscoverContract.Presenter {
    private Context context;

    IDiscoverContract.Model mModel;
    IDiscoverContract.View mView;

    LinkedList<Discover> momentList;

    public DiscoverPresenter(IDiscoverContract.View view, Context context) {
        this.context = context;
        this.mModel = new DiscoverModel();
        this.mView = view;
    }

    @Override
    public void showMomentList() {
        momentList = mModel.loadMomentList();
        mView.setMomentList(momentList);
    }

    @Override
    public void giveLike(int position) {

    }

    @Override
    public void cancelLike(int position) {

    }

    @Override
    public void makeComment(String content, int position) {

    }
}
