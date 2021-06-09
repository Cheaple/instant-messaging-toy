package com.example.im.mvp.contract;

import com.example.im.bean.chats.Chat;

import java.util.List;

public interface IChattingContract {
    interface View {
        public void showChatList(List list);
    }
    interface Presenter {
        
    }
    interface Model {
        public List loadChatList();
    }
}
