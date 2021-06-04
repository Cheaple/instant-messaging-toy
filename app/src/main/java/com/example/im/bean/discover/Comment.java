package com.example.im.bean.discover;

import java.util.ArrayList;

public class Comment {
    private String comment;  // 评论
    private String commenter;  // 评论者

    public Comment(String comment, String commenter) {
        this.comment = comment;
        this.commenter = commenter;
    }

    public String getComment() {
        return comment;
    }

    public String getCommenter() {
        return commenter;
    }
}
