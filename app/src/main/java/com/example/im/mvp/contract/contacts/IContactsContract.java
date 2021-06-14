package com.example.im.mvp.contract.contacts;

import com.example.im.bean.chats.Chat;
import com.example.im.bean.contacts.Contact;

import java.util.List;

public interface IContactsContract {
    interface View {
        void setContactList(List list);  // 初始化好友列表
        void setContactList();  // 更新好友列表
        void showText(String error);
    }
    interface Presenter {
        void showContactList();
        Contact getContact(int position);

    }
    interface Model {
        void loadContactList();
    }
}
