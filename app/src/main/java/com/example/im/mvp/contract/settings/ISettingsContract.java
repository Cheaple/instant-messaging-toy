package com.example.im.mvp.contract.settings;

public interface ISettingsContract {
    interface View {
        void gotoLoginActivity();
    }
    interface  Presenter {
        void changeAvatar();
        void changeName(String name);
        void changeID(String id);
        void changeRegion(String region);
        void changePassword(String old_pw, String new_pw, String confirm_pw);
        void logout();
    }
    interface Model {
        void changeAvatar();
        void changeName(String name);
        void changeID(String id);
        void changeRegion(String region);
        void changePassword(String new_pw);
        void logout();
    }
}
