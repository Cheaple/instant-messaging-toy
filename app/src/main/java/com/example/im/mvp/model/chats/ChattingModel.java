package com.example.im.mvp.model.chats;

import android.content.Context;

import com.example.im.R;
import com.example.im.bean.chats.Msg;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.chats.IChattingContract;

import java.util.LinkedList;
import java.util.List;

public class ChattingModel implements IChattingContract.Model {
    @Override
    public List loadMsgList(String id) {
        LinkedList<Msg> list = new LinkedList<>();
        // TODO: 从服务器加载历史消息
        list.add(new Msg(Msg.SPEAKER_TYPE_OTHER, "xixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixi"));  // for test
        return list;
    }

    @Override
    public Contact loadContactInfo(String id) {
        // TODO: 从服务器加载联系人信息
        return new Contact("000", R.drawable.avatar1, "Xixi", "xixi", "Shanghai");
    }

    @Override
    public void uploadMsg(String id) {
        // TODO: 向服务器上传新消息
    }
}
