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
    private String password;
    private String nickname;

    public SettingsPresenter(ISettingsContract.View view, Context context) {
        this.context = context;
        this.mModel = new SettingsModel(this);
        this.mView = view;
        this.username = AccountInfo.getInstance().getUsername();
        this.password = AccountInfo.getInstance().getPassword();
        this.nickname = AccountInfo.getInstance().getNickname();
        AccountInfo.getInstance().saveAccountInfo(context);
    }

    @Override
    public void showInfo() {
        mView.setUsername(username);
        mView.setNickname(nickname);
        // TODO: 设置昵称和头像
    }

    @Override
    public void changeAvatar() {
        mModel.changeAvatar();
    }

    @Override
    public void changeNickname(String new_nickname) {
        if ("".equals(new_nickname))
            mView.showText("昵称不能为空");
        else {
            mModel.changeNickname(new_nickname);
            nickname = new_nickname;
        }
    }

    @Override
    public void changeUsername(String new_username) {
        if ("".equals(new_username))
            mView.showText("ID不能为空");
        else {
            mModel.changeUsername(new_username);
            username = new_username;
        }
    }

    @Override
    public void changeRegion(String new_region) {
        mModel.changeRegion(new_region);
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
        else {
            mModel.changePassword(new_pw);
            password = new_pw;  // 暂时修改密码
        }
    }

    public void changeSuccess() {
        mView.showText("修改成功");
        AccountInfo.getInstance().setNickname(nickname);
        AccountInfo.getInstance().setAccount(username, password);
        AccountInfo.getInstance().saveAccountInfo(context);  // 保存新用户名或新密码
    }
    public void changeFailure(String error) {
        mView.showText(error);
        this.username = AccountInfo.getInstance().getUsername();  // 恢复原用户名
        this.password = AccountInfo.getInstance().getPassword();  // 恢复原密码
    }

    @Override
    public void logout() {
        mModel.logout();
        mView.gotoLoginActivity();
        AccountInfo.getInstance().clearAccountInfo(context);  // 清除用于自动登录的账户信息
    }
}
