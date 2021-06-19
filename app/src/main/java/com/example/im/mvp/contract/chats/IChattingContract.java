package com.example.im.mvp.contract.chats;


import com.example.im.bean.contacts.Contact;

import java.util.List;

public interface IChattingContract {
    interface View {
        void setMsgList(List list);
        void setMsgList();
        String getMsg();
        void clearMsg();
        void gotoInfoActivity(Contact contact, boolean isContact);
        void gotoGroupInfoActivity(String groupID);
        void showText(String error);
    }
    interface Presenter {
        void showMsgList();
        void sendMsg();
        void sendPicture(String path);
        void sendLocation();
        void checkInfo();  // 查看好友信息
    }
    interface Model {
        void loadMsgList(String id);
        void checkInfo(String username);
        void sendMsg(String id, String content);
    }
}
