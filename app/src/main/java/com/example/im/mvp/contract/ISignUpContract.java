package com.example.im.mvp.contract;

import com.example.im.listener.OnLoginListener;

public interface ISignUpContract {
    interface View extends ISignInContract.View{
        public String getConfirmPassword();
        public void clearConfirmPassword();
    }
    interface Presenter extends ISignInContract.Presenter {}
    interface Model extends  ISignInContract.Model {}
}
