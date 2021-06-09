package com.example.im.mvp.model;

import android.content.Context;

import com.example.im.R;
import com.example.im.bean.chats.Chat;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.IChatsContract;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChatsModel implements IChatsContract.Model {
    private Context context;

    public ChatsModel(Context context) {
        this.context = context;
    }

    @Override
    public List loadChatList() {
        LinkedList<Chat> list = new LinkedList<>();
        // TODO: 从服务器加载对话数据
        Contact contact1 = new Contact(context.getString(R.string.nickname1), R.drawable.avatar1);
        Contact contact2 = new Contact(context.getString(R.string.nickname2), R.drawable.avatar2);
        ArrayList<Contact> contactList = new ArrayList<>();
        contactList.add(contact1);
        contactList.add(contact2);
        list.add(new Chat(Chat.CHAT_TYPE_SINGLE, 101, context.getString(R.string.nickname1), R.drawable.avatar1, context.getString(R.string.sentence1), "2021/01/01"));
        list.add(new Chat(Chat.CHAT_TYPE_GROUP, 201, context.getString(R.string.nickname10), R.drawable.avatar10, context.getString(R.string.sentence2), "2021/01/01"));
        return list;
    }
}
