package com.example.im.mvp.contract.chats;

import com.example.im.bean.chats.Chat;

import java.util.List;

public interface IChatsContract {
    interface View {
        void setChatList(List list);  // 初始化会话列表
        void setChatList();  // 更新会话列表
        void showText(String error);
    }
    interface Presenter {
        void showChatList();
        Chat getChat(int position);
    }
    interface Model {
        void loadChatList();
    }
}
