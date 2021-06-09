package com.example.im.mvp.model.contacts;

import com.example.im.R;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.contacts.IContactSearchContract;

import java.util.LinkedList;
import java.util.List;

public class ContactSearchModel implements IContactSearchContract.Model{

    @Override
    public List loadInvitationList() {
        LinkedList<Contact> list = new LinkedList<>();
        Contact contact1 = new Contact(R.drawable.avatar1, "球仔", "Daidai", "Chengdu");
        list.add(contact1);
        // TODO: 从服务器加载邀请数据
        return list;
    }

    @Override
    public Contact searchUser(String id) {
        // TODO: 根据id搜索用户
        return null;
    }
}
