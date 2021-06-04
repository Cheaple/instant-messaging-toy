package com.example.im.bean.discover;

import java.util.ArrayList;

public class Discover {
    private int avatarIcon;  //头像
    private String nickname;  //昵称
    private String text;  // 文字
    private String publishedTime;  // 发布时间
    private ArrayList<Integer> images;  // 图片
    private ArrayList<String> likes;  // 点赞
    private ArrayList<String> comments;  // 评论
    private ArrayList<String> commenter;  // 评论者

    public Discover(String nickname, int avatarIcon, String text, String publishedTime, ArrayList<Integer> images, ArrayList<String> likes, ArrayList<String> comments, ArrayList<String> commenter) {
        this.nickname = nickname;
        this.avatarIcon = avatarIcon;
        this.text = text;
        this.publishedTime = publishedTime;
        this.images = images;
        this.likes = likes;
        this.comments = comments;
        this.commenter = commenter;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAvatarIcon() {
        return avatarIcon;
    }

    public ArrayList<Integer> getImages() {
        return images;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public String getText() {
        return text;
    }

    public int getImageCount() {
        return images.size();
    }

    public ArrayList<String> getLikes() {
        return likes;
    }

    public ArrayList<String> getComments() {
        return comments;
    }
    public ArrayList<String> getCommenter() {
        return commenter;
    }
}
