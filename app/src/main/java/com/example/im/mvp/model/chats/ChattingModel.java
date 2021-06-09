package com.example.im.mvp.model.chats;

import android.content.Context;

import com.example.im.R;
import com.example.im.bean.chats.Message;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.chats.IChattingContract;

import java.util.LinkedList;
import java.util.List;

public class ChattingModel implements IChattingContract.Model {
    private Context context;

    public ChattingModel(Context context) {
        this.context = context;
    }

    @Override
    public List loadMsgList(String id) {
        LinkedList<Message> list = new LinkedList<>();
        // TODO: 从服务器加载历史消息
        list.add(new Message(Message.SPEAKER_TYPE_OTHER, "xixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixi"));  // for test
        return list;
    }

    @Override
    public Contact loadContactInfo(String id) {
        // TODO: 从服务器加载联系人信息
        return new Contact(R.drawable.avatar1, context.getString(R.string.nickname3), "xixi", "Shanghai");
    }

    @Override
    public void uploadMsg(String id) {
        // TODO: 向服务器上传新消息
    }
}
