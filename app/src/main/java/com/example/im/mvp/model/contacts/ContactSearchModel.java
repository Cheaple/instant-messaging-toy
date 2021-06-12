package com.example.im.mvp.model.contacts;

import android.widget.Toast;

import com.example.im.R;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.contacts.IContactSearchContract;

import java.util.LinkedList;
import java.util.List;

public class ContactSearchModel implements IContactSearchContract.Model{
    @Override
    public List loadInvitationList() {
        LinkedList<Contact> list = new LinkedList<>();
        Contact contact1 = new Contact("003",R.drawable.avatar1, "球仔", "Daidai", "Chengdu");
        Contact contact2 = new Contact("004",R.drawable.avatar10, "三堆", "sandui", "Wuhan");
        list.add(contact1);
        list.add(contact2);
        // TODO: 从服务器加载邀请数据
        return list;
    }

    @Override
    public Contact searchUser(String username) {
        // TODO: 根据id搜索用户
        //return new Contact(R.drawable.avatar10, "何金龙", "111111111", "Zhengzhou");
        return null;
    }

    @Override
    public void accept(String id) {
        // TODO: 根据id删除该邀请，并添加好友
    }

    @Override
    public void refuse(String id) {
        // TODO: 根据id删除该邀请
    }
}
