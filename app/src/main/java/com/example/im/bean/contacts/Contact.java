package com.example.im.bean.contacts;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.im.R;

import java.io.Serializable;

public class Contact implements Parcelable {
    public static final int CONTACT_TYPE_LIST = 0;  // 已添加的联系人
    public static final int CONTACT_TYPE_SEARCH = 1;  // 未添加的联系人

    private String id;  // 唯一标识 ID
    private String avatar;  // 头像
    private String nickname;  // 昵称
    private String username;  // 用户名，用户眼里的 ID
    private String region;  // 地区

    public Contact(String id, String nickname, String username) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
    }

    public Contact(String id, String avatar, String nickname, String username) {
        this.id = id;
        this.avatar = avatar;
        this.nickname = nickname;
        this.username = username;
    }

    public Contact(String id, String avatar, String nickname, String username, String region) {
        this.id = id;
        this.avatar = avatar;
        this.nickname = nickname;
        this.username = username;
        this.region = region;
    }

    public String getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUsername() { return username; }

    public String getRegion() {
        return region;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", avatar=" + avatar +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //把数据写入Parcel
        dest.writeString(id);
        dest.writeString(avatar);
        dest.writeString(nickname);
        dest.writeString(username);
        dest.writeString(region);
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            //从Parcel中读取数据
            //此处read顺序依据write顺序
            return new Contact(source.readString(), source.readString(), source.readString(), source.readString(), source.readString());
        }
        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }

    };
}
