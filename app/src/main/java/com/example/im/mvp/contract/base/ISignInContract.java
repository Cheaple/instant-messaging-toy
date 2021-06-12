package com.example.im.mvp.contract.base;

import com.example.im.listener.OnLoginListener;


public interface ISignInContract {
    interface View {
        String getUsername();
        String getPassword();
        void clearUsername();
        void clearPassword();
        void gotoMainActivity();
    }
    interface Presenter {
        void login();
    }
    interface Model {
        void login(String username, String password, OnLoginListener loginListener);
    }
}
