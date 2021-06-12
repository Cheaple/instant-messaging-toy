package com.example.im.mvp.contract.settings;

public interface ISettingsContract {
    interface View {
        void gotoLoginActivity();
    }
    interface  Presenter {
        void changeAvatar();
        void changeNickname(String nickname);
        void changeUsername(String Username);
        void changeRegion(String region);
        void changePassword(String old_pw, String new_pw, String confirm_pw);
        void logout();
    }
    interface Model {
        void changeAvatar();
        void changeNickname(String nickname);
        void changeUsername(String username);
        void changeRegion(String region);
        void changePassword(String new_pw);
        void logout();
    }
}
