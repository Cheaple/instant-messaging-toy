package com.example.im.mvp.contract.settings;

public interface ISettingsContract {
    interface View {
        void setUsername(String username);
        void gotoLoginActivity();
        void showText(String content);
    }
    interface  Presenter {
        void showInfo();
        void changeAvatar();
        void changeNickname(String nickname);
        void changeUsername(String Username);
        void changeRegion(String region);
        void changePassword(String old_pw, String new_pw, String confirm_pw);
        void logout();
    }
    interface Model {
        void changeAvatar();
        void changeNickname(String username, String nickname);
        void changeUsername(String username, String new_username);
        void changeRegion(String username, String region);
        void changePassword(String username, String new_pw);
        void logout();
    }
}
