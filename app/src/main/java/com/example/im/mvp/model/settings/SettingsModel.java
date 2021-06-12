package com.example.im.mvp.model.settings;

import com.example.im.mvp.contract.settings.ISettingsContract;

public class SettingsModel implements ISettingsContract.Model {
    @Override
    public void changeAvatar() {
        // TODO: 更新头像
    }

    @Override
    public void changeNickname(String nickname) {
        // TODO: 更新昵称
    }

    @Override
    public void changeUsername(String username) {
        // TODO: 更新 username
    }

    @Override
    public void changeRegion(String region) {
        // TODO: 更新地区
    }

    @Override
    public void changePassword(String new_pw) {
        // TODO: 更新密码
    }

    @Override
    public void logout() {
        // TODO: 退出登录
    }
}
