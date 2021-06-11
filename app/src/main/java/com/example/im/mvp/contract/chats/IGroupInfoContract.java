package com.example.im.mvp.contract.chats;

import com.example.im.bean.contacts.Contact;

import java.util.List;

public interface IGroupInfoContract {
    interface View {
        void setMemberList(List list);  // 初始化成员列表
        void updateMemberList();  // 更新成员列表
        void gotoMainActivity();  // 用于在退出群聊后回到主界面
    }
    interface Presenter {
        void showMemberList();
        Contact getMember(int position);
        void delete();  // 退出群聊
    }
    interface Model {
        List loadMemberList();
        void delete(int id);  // 退出群聊
    }
}
