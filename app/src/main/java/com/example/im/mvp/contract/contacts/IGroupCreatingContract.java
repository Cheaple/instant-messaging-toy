package com.example.im.mvp.contract.contacts;

import com.example.im.bean.contacts.Contact;

import java.util.LinkedList;
import java.util.List;

public interface IGroupCreatingContract {
    public interface View {
        public void setContactList(List list);  // 初始化联系人列表
        public LinkedList<Contact> getSelectedContacts();  // 获取被选中的联系人
        public void gotoGroupChattingActivity();
    }
    public interface Presenter {
        public void showContactList();
        public void createGroup();
    }
    public interface Model {
        public List loadContactList();
        public void createGroup(LinkedList<Contact> selectedContacts);
    }
}
