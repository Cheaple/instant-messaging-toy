package com.example.im.mvp.model;

import android.content.Context;

import com.example.im.R;
import com.example.im.bean.chats.Chat;
import com.example.im.bean.chats.Message;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.IChattingContract;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChattingModel implements IChattingContract.Model {
    private Context context;

    public ChattingModel(Context context) {
        this.context = context;
    }

    @Override
    public List loadMsgList() {
        LinkedList<Message> list = new LinkedList<>();
        // TODO: 从服务器加载历史消息
        list.add(new Message(1, "xixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixi"));  // for test
        return list;
    }

    @Override
    public void uploadMsg() {
        // TODO: 向服务器上传新消息
    }
}
