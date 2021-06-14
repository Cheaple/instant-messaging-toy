package com.example.im.bean.chats;

public class Chat {
    public static final int CHAT_TYPE_SINGLE = 0x00001;  // 对话
    public static final int CHAT_TYPE_GROUP = 0x00002;  // 群聊

    private final int type;  // 类型：对话或群聊
    private final String id;  // id: 联系人id或群聊id
    private final String nickname;  // 昵称
    private final String lastSpeak;  // 最后聊天内容
    private final int avatarIcon;  // 头像
    private final String lastSpeakTime;  // 最后联络时间

    public Chat(int type, String id, String nickname, int avatarIcon, String lastSpeak, String lastSpeakTime) {
        this.type = type;
        this.id = id;
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
        this.lastSpeak = lastSpeak;
        this.lastSpeakTime = lastSpeakTime;
    }

    public int getType() { return type; }

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
}
