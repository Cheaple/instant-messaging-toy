package com.example.im.bean.discover;

import java.util.ArrayList;
import java.util.LinkedList;

public class Discover {
    private String id;
    private int avatarIcon;  // 头像
    private String publisher;  // 昵称
    private String momentType;  // 动态类型
    private String text;  // 文字
    private String time;  // 发布时间
    private ArrayList<Integer> images;  // 图片
    private int imageCount = 0;  // 图片数量
    private ArrayList<String> thumbs;  // 点赞
    private LinkedList<Reply> replies;  // 评论

    public Discover(String id, int avatarIcon, String publisher, String momentType, String text, String time,
                    ArrayList<Integer> images, int imageCount, ArrayList<String> thumbs, LinkedList<Reply> replies) {
        this.id = id;
        this.avatarIcon = avatarIcon;
        this.publisher = publisher;
        this.momentType = momentType;
        this.text = text;
        this.time = time;
        this.images = images;
        this.imageCount = imageCount;
        this.thumbs = thumbs;
        this.replies = replies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAvatarIcon() {
        return avatarIcon;
    }

    public void setAvatarIcon(int avatarIcon) {
        this.avatarIcon = avatarIcon;
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

    public ArrayList<Integer> getImages() {
        return images;
    }

    public void setImages(ArrayList<Integer> images) {
        this.images = images;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
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
                ", publisher='" + publisher + '\'' +
                ", momentType='" + momentType + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                ", thumbs=" + thumbs +
                ", replies=" + replies +
                '}';
    }
}
