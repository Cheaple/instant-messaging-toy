package com.example.im.mvp.presenter.settings;

import android.content.Context;

import com.example.im.bean.AccountInfo;
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

    private String username;

    public SettingsPresenter(ISettingsContract.View view, Context context) {
        this.context = context;
        this.mModel = new SettingsModel(this);
        this.mView = view;
        this.username = AccountInfo.getInstance().getUsername();
    }

    @Override
    public void changeAvatar() {
        mModel.changeAvatar();
    }

    @Override
    public void changeNickname(String nickname) {
        mModel.changeNickname(username, nickname);
    }

    @Override
    public void changeUsername(String new_username) {
        mModel.changeUsername(username, new_username);
    }

    @Override
    public void changeRegion(String region) {
        mModel.changeRegion(username, region);
    }

    @Override
    public void changePassword(String old_pw, String new_pw, String confirm_pw) {
        if (!(AccountInfo.getInstance().getPassword().equals(old_pw))) {  // 旧密码不正确
            mView.showText("请输入正确的原密码");
        }
        else if (new_pw.equals("")) {  // 新密码为空
            mView.showText("新密码不能为空");
        }
        else if (!new_pw.equals(confirm_pw)) {
            mView.showText("请确认新密码");
        }
        else mModel.changePassword(username, new_pw);
    }

    public void changeSuccess() {
        mView.showText("修改成功");
    }
    public void changeFailure(String error) {
        mView.showText(error);
    }

    @Override
    public void logout() {
        mModel.logout();
        mView.gotoLoginActivity();
        AccountInfo.getInstance().clearAccountInfo(context);  // 清除用于自动登录的账户信息
    }
}
