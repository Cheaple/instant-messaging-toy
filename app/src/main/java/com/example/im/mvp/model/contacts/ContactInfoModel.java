package com.example.im.mvp.model.contacts;

import androidx.annotation.Nullable;

import com.example.im.mvp.contract.contacts.IContactInfoContract;

public class ContactInfoModel implements IContactInfoContract.Model {
    @Override
    public void delete(String id) {
        //TODO：删除好友
    }

    @Override
    public void add(String id) {
        //TODO: 发送添加好友邀请
    }

    @Override
    public void clearChattingHistory(String id) {
        //TODO: 清空历史记录
   }
}
