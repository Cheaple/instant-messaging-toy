package com.example.im.bean.discover;

import java.util.ArrayList;

public class Reply {
    private String sender;  // 评论者
    private String text;  // 评论
    private String time;  // 评论时间

    public Reply(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public Reply(String sender, String text, String time) {
        this.sender = sender;
        this.text = text;
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Replies{" +
                "sender='" + sender + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
