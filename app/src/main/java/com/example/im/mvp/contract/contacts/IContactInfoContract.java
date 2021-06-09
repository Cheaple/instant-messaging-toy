package com.example.im.mvp.contract.contacts;

public interface IContactInfoContract {
    interface View {
        public void setAvatar(int avatar);
        public void setName(String name);
        public void setID(String id);
        public void setRegion(String region);
        public void gotoMainActivity();  // 用于在删除好友后回到主界面
    }
    interface Presenter {
        public void showInfo();
        public void clearChattingHistory();
        public void add();
        public void delete();
    }

    interface Model {
        public void clearChattingHistory(String id);
        public void add(String id);
        public void delete(String id);
    }
}
