package com.example.im.mvp.contract.chats;


import com.example.im.bean.contacts.Contact;

import java.util.List;

public interface IChattingContract {
    interface View {
        public void setMsgList(List list);
        public void setMsgList();
        public int getChatType();
        public String getMsg();
        public void clearMsg();
    }
    interface Presenter {
        public void showMsgList();
        public void sendMsg();
    }
    interface Model {
        public List loadMsgList(String id);
        public Contact loadContactInfo(String id);
        public void uploadMsg(String id);
    }
}
