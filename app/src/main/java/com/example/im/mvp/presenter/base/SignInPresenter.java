package com.example.im.mvp.presenter.base;

import com.example.im.bean.AccountInfo;
import com.example.im.listener.OnLoginListener;
import com.example.im.mvp.contract.base.ISignInContract;
import com.example.im.mvp.model.base.SignInModel;

public class SignInPresenter implements ISignInContract.Presenter {
    private ISignInContract.Model mModel;
    private ISignInContract.View mView;

    public SignInPresenter(ISignInContract.View view) {
        this.mModel = new SignInModel();
        this.mView = view;
    }

    @Override
    public void login() {
        String username = mView.getUsername();
        String password = mView.getPassword();
        mModel.login(username, password, new OnLoginListener() {
            @Override
            public void loginSuccess(AccountInfo account) {

            }

            @Override
            public void loginFailed() {

            }
        });
    }
}
