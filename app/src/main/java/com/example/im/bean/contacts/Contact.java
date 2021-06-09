package com.example.im.bean.contacts;


import java.io.Serializable;

public class Contact implements Serializable {
    public static final int CONTACT_TYPE_LIST = 0;  // 已添加的联系人
    public static final int CONTACT_TYPE_SEARCH = 1;  // 未添加的联系人

    private int avatarIcon;  // 头像
    private String nickname;  // 昵称
    private String id;  // ID
    private String region;  // 地区

    public Contact(int avatarIcon, String nickname, String id, String region) {
        this.avatarIcon = avatarIcon;
        this.nickname = nickname;
        this.id = id;
        this.region = region;
    }

    public int getAvatarIcon() {
        return avatarIcon;
    }

    public String getNickname() {
        return nickname;
    }

    public String getID() {
        return id;
    }

    public String getRegion() {
        return region;
    }
}
