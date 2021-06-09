package com.example.im.mvp.presenter;

import com.example.im.bean.AccountInfo;
import com.example.im.listener.OnLoginListener;
import com.example.im.mvp.contract.base.ISignInContract;
import com.example.im.mvp.model.SignInModel;

public class SignInPresenter implements ISignInContract.Presenter {
    private ISignInContract.Model mModel;
    private ISignInContract.View mView;

    public SignInPresenter(ISignInContract.View view) {
        this.mModel = new SignInModel();
        this.mView = view;
    }

    @Override
    public void login() {
        String id = mView.getID();
        String password = mView.getPassword();
        mModel.login(id, password, new OnLoginListener() {
            @Override
            public void loginSuccess(AccountInfo account) {

            }

            @Override
            public void loginFailed() {

            }
        });
    }
}
