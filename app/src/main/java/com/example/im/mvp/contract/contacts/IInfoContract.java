package com.example.im.mvp.contract.contacts;

import com.example.im.bean.contacts.Contact;

public interface IInfoContract {
    interface View {
        void setAvatar(String avatar);
        void setNickname(String nickname);
        void setUsername(String username);
        void setRegion(String region);
        void gotoMainActivity();  // 用于在删除好友后回到主界面
        void gotoInfoActivity(Contact contact);  // 用于在添加好友后前往好友信息界面
        void gotoChattingActivity(Contact contact, String chattingId);  // 用于向好友发起会话
        void showText(String content);
    }
    interface Presenter {
        void showInfo();
        void createChatting();
        void clearChattingHistory();
        void add();
        void delete();
    }

    interface Model {
        void createChatting(String username, String target);
        void clearChattingHistory(String username);
        void add(String username);
        void delete(String username);
    }
}
