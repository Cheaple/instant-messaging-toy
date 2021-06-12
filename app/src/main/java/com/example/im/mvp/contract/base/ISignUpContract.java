package com.example.im.mvp.contract.base;

import com.example.im.listener.OnLoginListener;

public interface ISignUpContract {
    interface View extends ISignInContract.View{
        String getConfirmPassword();
        void clearConfirmPassword();
    }
    interface Presenter extends ISignInContract.Presenter {
        void login();
    }
    interface Model extends  ISignInContract.Model {
        void login(String username, String password);
    }
}
