package com.example.im.mvp.contract.contacts;

public interface IContactInfoContract {
    interface View {
        void setAvatar(int avatar);
        void setName(String name);
        void setID(String id);
        void setRegion(String region);
        void gotoMainActivity();  // 用于在删除好友后回到主界面
    }
    interface Presenter {
        void showInfo();
        void clearChattingHistory();
        void add();
        void delete();
    }

    interface Model {
        void clearChattingHistory(String id);
        void add(String id);
        void delete(String id);
    }
}
