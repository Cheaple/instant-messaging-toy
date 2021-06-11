package com.example.im.mvp.model.contacts;

import android.content.Context;

import com.example.im.R;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.contacts.IContactsContract;

import java.util.LinkedList;
import java.util.List;

public class ContactsModel implements IContactsContract.Model {
    @Override
    public List loadContactList() {
        LinkedList<Contact> list = new LinkedList<>();
        // TODO: 从服务器加载对话数据
        Contact contact1 = new Contact(R.drawable.avatar1, "Daidai", "Daidai", "Chengdu");
        list.add(contact1);
        return list;
    }
}
