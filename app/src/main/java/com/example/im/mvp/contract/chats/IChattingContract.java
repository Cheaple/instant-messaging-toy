package com.example.im.mvp.contract.chats;


import com.example.im.bean.contacts.Contact;

import java.util.List;

public interface IChattingContract {
    interface View {
        void setMsgList(List list);
        void setMsgList();
        int getChatType();
        String getMsg();
        void clearMsg();
    }
    interface Presenter {
        void showMsgList();
        void sendMsg();
    }
    interface Model {
        List loadMsgList(String id);
        Contact loadContactInfo(String id);
        void uploadMsg(String id);
    }
}
