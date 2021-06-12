package com.example.im.mvp.model.chats;

import android.content.Context;

import com.example.im.R;
import com.example.im.bean.chats.Chat;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.chats.IChatsContract;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChatsModel implements IChatsContract.Model {
    @Override
    public List loadChatList() {
        LinkedList<Chat> list = new LinkedList<>();
        // TODO: 从服务器加载对话数据
        Contact contact1 = new Contact("000", R.drawable.avatar1, "Daidai", "Daidai", "Chengdu");
        Contact contact2 = new Contact("001", R.drawable.avatar2, "Jige", "Jige", "Wuhan");
        ArrayList<Contact> contactList = new ArrayList<>();
        contactList.add(contact1);
        contactList.add(contact2);
        list.add(new Chat(Chat.CHAT_TYPE_SINGLE, 101, "Daidai", R.drawable.avatar1, "6666666", "2021/01/01"));
        list.add(new Chat(Chat.CHAT_TYPE_GROUP, 201, "Jige", R.drawable.avatar10,"6666666", "2021/01/01"));
        return list;
    }
}
