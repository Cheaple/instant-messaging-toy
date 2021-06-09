package com.example.im.mvp.contract.chats;


import java.util.List;

public interface IChattingContract {
    interface View {
        public void setMsgList(List list);
        public void setMsgList();
        public String getMsg();
        public void clearMsg();
    }
    interface Presenter {
        public void showMsgList();
        public void sendMsg();
    }
    interface Model {
        public List loadMsgList();
        public void uploadMsg();
    }
}
