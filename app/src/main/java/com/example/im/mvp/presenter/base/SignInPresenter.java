package com.example.im.mvp.presenter.base;

import android.content.Context;

import com.example.im.bean.AccountInfo;
import com.example.im.listener.OnLoginListener;
import com.example.im.mvp.contract.base.ISignInContract;
import com.example.im.mvp.model.base.SignInModel;

public class SignInPresenter implements ISignInContract.Presenter {
    private Context context;

    private ISignInContract.Model mModel;
    private ISignInContract.View mView;

    public SignInPresenter(ISignInContract.View view, Context context) {
        this.context = context;
        this.mModel = new SignInModel(this);
        this.mView = view;
    }

    @Override
    public void login() {
        String username = mView.getUsername();
        String password = mView.getPassword();
        if ("".equals(username))
            mView.showText("请输入用户名");
        else if ("".equals(password))
            mView.showText("请输入密码");
        else {
            AccountInfo.getInstance().setAccount(username, password);
            mModel.login(username, password);
        }
    }

    public void loginSuccess() {
        AccountInfo.getInstance().saveAccountInfo(context);
        mView.gotoMainActivity();
    }
    public void loginFailure(String error) {
        mView.showText(error);
        AccountInfo.getInstance().clearAccountInfo(context);
    }

    @Override
    public void autoLogin() {
        if (AccountInfo.getInstance().ifLoggedIn(context))
            mModel.login(AccountInfo.getInstance().getUsername(), AccountInfo.getInstance().getPassword());
    }
}
