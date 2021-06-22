package com.example.im.bean.chats;

import java.util.ArrayList;
import java.util.Arrays;

public class Chat {
    public static final String CHAT_TYPE_SINGLE = "PRIVATE_CHAT";  // 对话
    public static final String CHAT_TYPE_GROUP = "GROUP_CHAT";  // 群聊

    private final String type;  // 类型：对话或群聊
    private final String id;  // id: 联系人id或群聊id
    private final String nickname;  // 昵称
    private String lastSpeak;  // 最后聊天内容
    private int avatarIcon;  // 头像
    private String lastSpeakTime;  // 最后联络时间

    private ArrayList<String> memberIdList;

    public Chat(String type, String id, String nickname, String[] members) {
        this.type = type;
        this.id = id;
        this.nickname = nickname;
        this.memberIdList = new ArrayList<String>(Arrays.asList(members));
    }

    public Chat(String type, String id, String nickname, int avatarIcon, String lastSpeak, String lastSpeakTime) {
        this.type = type;
        this.id = id;
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
        this.lastSpeak = lastSpeak;
        this.lastSpeakTime = lastSpeakTime;
    }

    public String getType() { return type; }

    public String getId() {return id; }

    public int getAvatarIcon() {
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
}
