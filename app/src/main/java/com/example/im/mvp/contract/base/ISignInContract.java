package com.example.im.mvp.contract.base;

import com.example.im.listener.OnLoginListener;


public interface ISignInContract {
    interface View {
        public String getID();
        public String getPassword();
        public void clearID();
        public  void clearPassword();
        public void gotoMainActivity();
    }
    interface Presenter {
        public void login();
    }
    interface Model {
        public void login(String id, String password, OnLoginListener loginListener);
    }
}
