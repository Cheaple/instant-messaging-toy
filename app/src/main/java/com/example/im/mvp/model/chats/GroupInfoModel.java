package com.example.im.mvp.model.chats;

import com.example.im.R;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.chats.IGroupInfoContract;

import java.util.LinkedList;
import java.util.List;

public class GroupInfoModel implements IGroupInfoContract.Model {
    @Override
    public List loadMemberList() {
        LinkedList<Contact> list = new LinkedList<>();
        // TODO: 从服务器加载群聊成员数据
        Contact contact1 = new Contact("000", R.drawable.avatar1, "Member1", "Daidai", "Chengdu");
        Contact contact2 = new Contact("001", R.drawable.avatar1, "Member1", "Daidai", "Chengdu");
        list.add(contact1);
        list.add(contact2);
        return list;
    }

    @Override
    public void delete(String id) {
        // TODO: 退出群聊
    }
}
