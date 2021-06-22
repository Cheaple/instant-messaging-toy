package com.example.im.mvp.contract.chats;


import com.example.im.bean.chats.Msg;
import com.example.im.bean.contacts.Contact;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public interface IChattingContract {
    interface View {
        void setMsgList(LinkedList<Msg> msgList, ArrayList<Contact> memberList);
        void updateMsgList();
        String getMsg();
        void clearMsg();
        void gotoInfoActivity(Contact contact, boolean isContact);
        void gotoGroupInfoActivity(String groupID);
        void showText(String error);
    }
    interface Presenter {
        void showMsgList();
        void loadMemberInfo(ArrayList<String> members);
        void sendMsg();
        void sendPicture(String path);
        void sendVideo(String path);
        void sendLocation();
        void checkInfo();  // 查看好友信息
    }
    interface Model {
        void loadMsgList(String id);
        void loadMemberInfo(ArrayList<String> members);
        void checkInfo(String username);
        void send(String chatID, String content, int msgType);
        void sendLocation(String id);
        void upload(String fileType, String file);
    }
}
