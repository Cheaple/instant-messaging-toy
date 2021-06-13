package com.example.im.bean.contacts;


import com.example.im.R;

import java.io.Serializable;

public class Contact implements Serializable {
    public static final int CONTACT_TYPE_LIST = 0;  // 已添加的联系人
    public static final int CONTACT_TYPE_SEARCH = 1;  // 未添加的联系人

    private String id;  // 唯一标识 ID
    private int avatarIcon;  // 头像
    private String nickname;  // 昵称
    private String username;  // 用户名，用户眼里的 ID
    private String region;  // 地区

    public Contact(String id, String nickname, String username) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
    }

    public Contact(String id, int avatarIcon, String nickname, String username, String region) {
        this.id = id;
        this.avatarIcon = avatarIcon;
        this.nickname = nickname;
        this.username = username;
        this.region = region;
    }

    public String getID() {
        return id;
    }

    public int getAvatarIcon() {
        return avatarIcon;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUsername() { return username; }

    public String getRegion() {
        return region;
    }
}
