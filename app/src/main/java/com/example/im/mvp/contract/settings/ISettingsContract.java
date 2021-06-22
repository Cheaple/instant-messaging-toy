package com.example.im.mvp.contract.settings;

public interface ISettingsContract {
    interface View {
        void setAvatar(String avatar);
        void setUsername(String username);
        void setNickname(String nickname);
        void gotoLoginActivity();
        void showText(String content);
    }
    interface  Presenter {
        void showInfo();
        void changeAvatar(String avatar);  // 新头像上传成功后
        void changeNickname(String nickname);
        void changeUsername(String Username);
        void changeRegion(String region);
        void changePassword(String old_pw, String new_pw, String confirm_pw);
        void logout();
    }
    interface Model {
        void changeAvatar(String new_avatar);
        void upload(String file);
        void changeNickname(String new_nickname);
        void changeUsername(String new_username);
        void changeRegion(String new_region);
        void changePassword(String new_pw);
        void logout();
    }
}
