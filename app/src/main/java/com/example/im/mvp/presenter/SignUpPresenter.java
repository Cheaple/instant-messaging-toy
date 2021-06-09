package com.example.im.mvp.presenter;

import com.example.im.bean.AccountInfo;
import com.example.im.listener.OnLoginListener;
import com.example.im.mvp.contract.ISignUpContract;
import com.example.im.mvp.model.SignUpModel;

public class SignUpPresenter implements ISignUpContract.Presenter {
    private ISignUpContract.Model mModel;
    private ISignUpContract.View mView;

    public SignUpPresenter(ISignUpContract.View view) {
        this.mModel = new SignUpModel();
        this.mView = view;
    }

    @Override
    public void login() {
        String id = mView.getID();
        String password = mView.getPassword();
        String confirmPassword = mView.getConfirmPassword();
        if (password.equals(confirmPassword)) {
            mModel.login(id, password, new OnLoginListener() {
                @Override
                public void loginSuccess(AccountInfo account) {

                }

                @Override
                public void loginFailed() {
//Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {

        }
    }
}
