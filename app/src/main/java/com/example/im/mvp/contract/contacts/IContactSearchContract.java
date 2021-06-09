package com.example.im.mvp.contract.contacts;

import com.example.im.bean.contacts.Contact;

import java.util.List;

public interface IContactSearchContract {
    public interface View{
        public void setInvitationList(List list);  // 初始化好友邀请列表
        public String getTargetID();
    }
    public interface Presenter {
        public void showInvitationList();
        public void searchUser();
    }
    public interface Model {
        public List loadInvitationList();
        public Contact searchUser(String id);
    }
}
