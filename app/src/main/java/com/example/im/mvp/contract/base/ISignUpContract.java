package com.example.im.mvp.contract.base;

public interface ISignUpContract {
    interface View extends ISignInContract.View{
        String getConfirmPassword();
        void clearConfirmPassword();
    }
    interface Presenter extends ISignInContract.Presenter {}
    interface Model extends  ISignInContract.Model {}
}
