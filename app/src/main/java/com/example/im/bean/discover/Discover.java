package com.example.im.bean.discover;

import java.util.ArrayList;
import java.util.LinkedList;

public class Discover {
    private String id;
    private String avatar;  // 头像
    private String publisher;  // 昵称
    private String momentType;  // 动态类型
    private String text;  // 文字
    private String time;  // 发布时间
    private ArrayList<String> pictures;  // 图片
    private String video;  // 视频
    private int imageCount = 0;  // 图片数量
    private ArrayList<String> thumbs;  // 点赞
    private LinkedList<Reply> replies;  // 评论


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getMomentType() {
        return momentType;
    }

    public void setMomentType(String momentType) {
        this.momentType = momentType;
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

    public void setPublishedTime(String time) {
        this.time = time;
    }

    public ArrayList<String> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<String> pictures) {
        this.pictures = pictures;
    }

    public int getPicturesCnt() {
        return pictures.size();
    }

    public String getVideo() {
        return video;
    }

    public ArrayList<String> getThumbs() {
        return thumbs;
    }

    public void setThumbs(ArrayList<String> likes) {
        this.thumbs = thumbs;
    }

    public LinkedList<Reply> getReplies() {
        return replies;
    }

    public void setReplies(LinkedList<Reply> replies) {
        this.replies = replies;
    }

    @Override
    public String toString() {
        return "Discover{" +
                "id='" + id + '\'' +
                ", avatar='" + avatar + '\'' +
                ", publisher='" + publisher + '\'' +
                ", momentType='" + momentType + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                ", pictures=" + pictures +
                ", video='" + video + '\'' +
                ", imageCount=" + imageCount +
                ", thumbs=" + thumbs +
                ", replies=" + replies +
                '}';
    }
}
