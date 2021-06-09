package com.example.im.mvp.contract;

import com.example.im.bean.chats.Chat;

import java.util.List;

public interface IChatsContract {
    interface View {
        public void showChatList(List list);
    }
    interface Presenter {
        public Chat getChat(int position);
    }
    interface Model {
        public List loadChatList();
    }
}
