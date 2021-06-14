package com.example.im.mvp.contract.base;

import com.example.im.listener.OnLoginListener;


public interface ISignInContract {
    interface View {
        String getUsername();
        String getPassword();
        void clearUsername();
        void clearPassword();
        void gotoMainActivity();
        void showText(String content);
    }
    interface Presenter {
        void login();
        void autoLogin();
    }
    interface Model {
        void login(String username, String password);
        void loadInfo(String username);
    }
}
