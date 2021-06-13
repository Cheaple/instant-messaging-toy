package com.example.im.mvp.contract.contacts;

import com.example.im.bean.contacts.Contact;

import java.util.List;

public interface ISearchContract {
    interface View{
        void setInvitationList(List list);  // 初始化好友邀请列表
        void setInvitationList();  // 刷新好友邀请列表
        String getTargetUsername();
        void gotoInfoActivity(Contact contact, boolean isContact);  // 搜索成功，查看该用户信息
        void showText(String content);
    }
    interface Presenter {
        void showInvitationList();
        void searchUser();
        void accept(int position);  // 接收好友邀请
        void refuse(int position);  // 拒绝好友邀请
    }
    interface Model {
        void loadInvitationList();
        void searchUser(String username, String target);
        void accept(String id);  // 接收好友邀请
        void refuse(String id);  // 拒绝好友邀请
    }
}
