package com.example.im.mvp.contract.chats;

import com.example.im.bean.chats.Chat;

import java.util.List;

public interface IChatsContract {
    interface View {
        public void setChatList(List list);  // 初始化会话列表
        public void setChatList();  // 更新会话列表
    }
    interface Presenter {
        public void showChatList();
        public Chat getChat(int position);
    }
    interface Model {
        public List loadChatList();
    }
}
