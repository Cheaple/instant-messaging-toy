package com.example.im.mvp.contract.base;

import com.example.im.listener.OnLoginListener;


public interface ISignInContract {
    interface View {
        String getID();
        String getPassword();
        void clearID();
        void clearPassword();
        void gotoMainActivity();
    }
    interface Presenter {
        void login();
    }
    interface Model {
        void login(String id, String password, OnLoginListener loginListener);
    }
}
