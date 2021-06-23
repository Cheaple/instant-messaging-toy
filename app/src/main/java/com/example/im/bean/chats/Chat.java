package com.example.im.bean.chats;

import java.util.ArrayList;
import java.util.Arrays;

public class Chat {
    public static final String CHAT_TYPE_SINGLE = "PRIVATE_CHAT";  // 对话
    public static final String CHAT_TYPE_GROUP = "GROUP_CHAT";  // 群聊

    private final String type;  // 类型：对话或群聊
    private final String id;  // id: 联系人id或群聊id
    private String nickname;  // 昵称
    private String lastSpeak;  // 最后聊天内容
    private String avatarIcon;  // 头像
    private String lastSpeakTime;  // 最后联络时间

    private ArrayList<String> memberIdList;

    public Chat(Chat chat, String nickname, String avatarIcon) {
        this.type = chat.type;
        this.id = chat.id;
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
        this.lastSpeak = chat.lastSpeak;
        this.lastSpeakTime = chat.lastSpeakTime;
    }

    public Chat(String type, String id, String nickname, String[] members) {
        this.type = type;
        this.id = id;
        this.nickname = nickname;
        this.memberIdList = new ArrayList<String>(Arrays.asList(members));
    }

    public Chat(String type, String id, String nickname, String avatarIcon, String lastSpeak, String lastSpeakTime) {
        this.type = type;
        this.id = id;
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
        this.lastSpeak = lastSpeak;
        this.lastSpeakTime = lastSpeakTime;
    }

    public String getType() { return type; }

    public String getId() {return id; }

    public String getAvatarIcon() {
        return avatarIcon;
    }

    public String getLastSpeak() {
        return lastSpeak;
    }

    public String getLastSpeakTime() {
        return lastSpeakTime;
    }

    public String getNickname() {
        return nickname;
    }

    public ArrayList<String> getMemberIdList() {
        return memberIdList;
    }

    public void setLastSpeak(String lastSpeak) {
        this.lastSpeak = lastSpeak;
    }

    public void setAvatarIcon(String avatarIcon) {
        this.avatarIcon = avatarIcon;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setLastSpeakTime(String lastSpeakTime) {
        this.lastSpeakTime = lastSpeakTime;
    }

    public void setMemberIdList(ArrayList<String> memberIdList) {
        this.memberIdList = memberIdList;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", lastSpeak='" + lastSpeak + '\'' +
                ", avatarIcon='" + avatarIcon + '\'' +
                ", lastSpeakTime='" + lastSpeakTime + '\'' +
                ", memberIdList=" + memberIdList +
                '}';
    }
}
