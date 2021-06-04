package com.example.im.bean.chats;

public class Message {
    private int speaker;  // 0为自己，1为对方
    private String content;  // 评论者

    public Message(int speaker, String content) {
        this.speaker = speaker;
        this.content = content;
    }

    public int getSpeaker() {
        return speaker;
    }

    public String getContent() {
        return content;
    }
}
