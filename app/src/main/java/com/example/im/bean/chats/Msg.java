package com.example.im.bean.chats;

public class Msg {
    public final static int SPEAKER_TYPE_ME = 0;
    public final static int SPEAKER_TYPE_OTHER = 1;

    private int speaker;  // 0为自己，1为对方
    private String content;  // 评论者

    public Msg(int speaker, String content) {
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
