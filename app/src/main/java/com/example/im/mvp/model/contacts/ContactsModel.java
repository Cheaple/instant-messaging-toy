package com.example.im.mvp.model.contacts;

import android.content.Context;

import com.example.im.R;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.contacts.IContactsContract;

import java.util.LinkedList;
import java.util.List;

public class ContactsModel implements IContactsContract.Model {
    private Context context;

    public ContactsModel(Context context) {
        this.context = context;
    }

    @Override
    public List loadContactList() {
        LinkedList<Contact> list = new LinkedList<>();
        // TODO: 从服务器加载对话数据
        Contact contact1 = new Contact(context.getString(R.string.nickname1), R.drawable.avatar1);
        list.add(contact1);
        return list;
    }
}
