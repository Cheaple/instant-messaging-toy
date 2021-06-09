package com.example.im.mvp.model.chats;

import android.content.Context;

import com.example.im.bean.chats.Message;
import com.example.im.mvp.contract.chats.IChattingContract;

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
        list.add(new Message(Message.SPEAKER_TYPE_OTHER, "xixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixi"));  // for test
        return list;
    }

    @Override
    public void uploadMsg() {
        // TODO: 向服务器上传新消息
    }
}
