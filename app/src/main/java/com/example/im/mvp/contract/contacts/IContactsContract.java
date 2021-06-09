package com.example.im.mvp.contract.contacts;

import com.example.im.bean.chats.Chat;
import com.example.im.bean.contacts.Contact;

import java.util.List;

public interface IContactsContract {
    public interface View {
        public void setContactList(List list);  // 初始化好友列表
        public void setContactList();  // 更新好友列表
    }
    public interface Presenter {
        public void showContactList();
        public Contact getContact(int position);

    }
    public interface Model {
        public List loadContactList();
    }
}
