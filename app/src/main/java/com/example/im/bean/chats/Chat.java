package com.example.im.bean.chats;

public class Chat {
    private final int type;  // 类型：对话或群聊
    private final String nickname; // 昵称
    private final String lastSpeak; //最后聊天内容
    private final int avatarIcon; // 头像
    private final String lastSpeakTime; //最后联络时间

    public Chat(String nickname, int avatarIcon, String lastSpeak, String lastSpeakTime, int type) {
        this.type = type;
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
        this.lastSpeak = lastSpeak;
        this.lastSpeakTime = lastSpeakTime;
    }

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
}
