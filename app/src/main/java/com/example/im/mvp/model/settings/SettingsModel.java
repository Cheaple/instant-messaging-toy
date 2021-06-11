package com.example.im.mvp.model.settings;

import com.example.im.mvp.contract.settings.ISettingsContract;

public class SettingsModel implements ISettingsContract.Model {
    @Override
    public void changeAvatar() {
        // TODO: 更新头像
    }

    @Override
    public void changeName(String name) {
        // TODO: 更新昵称
    }

    @Override
    public void changeID(String id) {
        // TODO: 更新id
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
