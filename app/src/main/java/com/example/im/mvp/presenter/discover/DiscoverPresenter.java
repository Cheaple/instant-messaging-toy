package com.example.im.mvp.presenter.discover;

import android.content.Context;

import com.example.im.bean.chats.Chat;
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
        this.mModel = new DiscoverModel(this);
        this.mView = view;
    }

    @Override
    public void showMomentList() {
        mModel.loadMomentList();
    }

    public void loadSuccess(LinkedList<Discover> momentList) {
        this.momentList = momentList;
        mView.setMomentList(momentList);
    }

    @Override
    public void giveLike(int position) {
        mModel.giveLike(momentList.get(position).getId());
    }

    public void giveSuccess() {

    }


    @Override
    public void cancelLike(int position) {
        mModel.cancelLike(momentList.get(position).getId());
    }

    public void cancelSuccess() {

    }


    @Override
    public void makeComment(String content, int position) {
        mModel.makeComment(momentList.get(position).getId(), content);
    }

    public void commentSuccess() {

    }

    public void discoverFailure(String error) {
        mView.showText(error);
    }
}
