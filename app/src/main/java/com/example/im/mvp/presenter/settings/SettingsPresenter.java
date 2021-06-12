package com.example.im.mvp.presenter.settings;

import android.content.Context;

import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.contacts.IGroupCreatingContract;
import com.example.im.mvp.contract.settings.ISettingsContract;
import com.example.im.mvp.model.contacts.GroupCreatingModel;
import com.example.im.mvp.model.settings.SettingsModel;

import java.util.LinkedList;

public class SettingsPresenter implements ISettingsContract.Presenter {
    private Context context;

    ISettingsContract.Model mModel;
    ISettingsContract.View mView;

    public SettingsPresenter(ISettingsContract.View view, Context context) {
        this.context = context;
        this.mModel = new SettingsModel();
        this.mView = view;
    }

    @Override
    public void changeAvatar() {
        mModel.changeAvatar();
    }

    @Override
    public void changeNickname(String nickname) {
        mModel.changeNickname(nickname);
    }

    @Override
    public void changeUsername(String username) {
        mModel.changeUsername(username);
    }

    @Override
    public void changeRegion(String region) {
        mModel.changeRegion(region);
    }

    @Override
    public void changePassword(String old_pw, String new_pw, String confirm_pw) {
        if (true) {  // 如果密码不正确

        }
        else if (new_pw != confirm_pw) {

        }
        else mModel.changePassword(new_pw);
    }

    @Override
    public void logout() {
        mModel.logout();
        mView.gotoLoginActivity();
    }
}
