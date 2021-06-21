package com.example.im.mvp.presenter.base;

import android.content.Context;

import com.example.im.bean.AccountInfo;
import com.example.im.bean.contacts.Contact;
import com.example.im.listener.OnLoginListener;
import com.example.im.mvp.contract.base.ISignUpContract;
import com.example.im.mvp.model.base.SignUpModel;

public class SignUpPresenter implements ISignUpContract.Presenter {
    private Context context;

    private ISignUpContract.Model mModel;
    private ISignUpContract.View mView;

    public SignUpPresenter(ISignUpContract.View view, Context context) {
        this.context = context;
        this.mModel = new SignUpModel(this);
        this.mView = view;
    }

    @Override
    public void login() {
        String username = mView.getUsername();
        String password = mView.getPassword();
        String confirmPassword = mView.getConfirmPassword();
        if ("".equals(username))
            mView.showText("请输入用户名");
        else if ("".equals(password))
            mView.showText("请输入密码");
        else if (!password.equals(confirmPassword)) {
            mView.showText("请确认密码");
            mView.clearConfirmPassword();
        }
        else {
            AccountInfo.getInstance().setAccount(username, password);
            mModel.login(username, password);
        }
    }

    public void loginSuccess() {
        mView.gotoMainActivity();
        AccountInfo.getInstance().saveAccountInfo(context);
    }
    public void loginFailure(String error) {
        mView.showText(error);
        AccountInfo.getInstance().clearAccountInfo(context);}

    public void loadSuccess(Contact contact) {
        AccountInfo.getInstance().setNickname(contact.getNickname());  // 设置全局使用的昵称
        AccountInfo.getInstance().setAvatar(contact.getAvatar());  // 设置全局使用的头像
        mView.gotoMainActivity();
    }
    public void loadFailure(String error) {
        mView.showText(error);
    }

    @Override
    public void autoLogin() {
        if (AccountInfo.getInstance().ifLoggedIn(context))
            mModel.login(AccountInfo.getInstance().getUsername(), AccountInfo.getInstance().getPassword());
    }
}
