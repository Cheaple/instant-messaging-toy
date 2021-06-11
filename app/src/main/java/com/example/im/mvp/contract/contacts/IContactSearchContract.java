package com.example.im.mvp.contract.contacts;

import com.example.im.bean.contacts.Contact;

import java.util.List;

public interface IContactSearchContract {
    interface View{
        void setInvitationList(List list);  // 初始化好友邀请列表
        void setInvitationList();  // 刷新好友邀请列表
        String getTargetID();
        void gotoContactInfoActivity(Contact contact);  // 搜索成功，查看该用户信息
        void searchFailed();  // 搜索失败
    }
    interface Presenter {
        void showInvitationList();
        void searchUser();
        void accept(int position);  // 接收好友邀请
        void refuse(int position);  // 拒绝好友邀请
    }
    interface Model {
        List loadInvitationList();
        Contact searchUser(String id);
        void accept(String id);  // 接收好友邀请
        void refuse(String id);  // 拒绝好友邀请
    }
}
