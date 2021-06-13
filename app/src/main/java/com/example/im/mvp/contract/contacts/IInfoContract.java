package com.example.im.mvp.contract.contacts;

import com.example.im.bean.contacts.Contact;

public interface IInfoContract {
    interface View {
        void setAvatar(int avatar);
        void setNickname(String nickname);
        void setUsername(String username);
        void setRegion(String region);
        void gotoMainActivity();  // 用于在删除好友后回到主界面
        void gotoInfoActivity(Contact contact);  // 用于在添加好友后前往好友信息界面
        void showText(String content);
    }
    interface Presenter {
        void showInfo();
        void clearChattingHistory();
        void add();
        void delete();
    }

    interface Model {
        void clearChattingHistory(String id);
        void add(String username, String target);
        void delete(String username, String target);
    }
}
