package com.example.im.mvp.model.contacts;

import android.content.Context;

import com.example.im.R;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.contacts.IGroupCreatingContract;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GroupCreatingModel implements IGroupCreatingContract.Model {
    @Override
    public List loadContactList() {
        LinkedList<Contact> list = new LinkedList<>();
        // TODO: 从服务器加载对话数据
        Contact contact1 = new Contact(R.drawable.avatar1, "Xixi", "Daidai", "Chengdu");
        Contact contact2 = new Contact(R.drawable.avatar2, "Jiyu", "JiYu", "Lanzhou");
        list.add(contact1);
        list.add(contact2);
        return list;
    }

    @Override
    public void createGroup(ArrayList<String> selectedContacts) {
        // TODO: 创建群聊
    }

    @Override
    public void inviteContacts(int groupID, ArrayList<String> selectedContacts) {
        // TODO: 邀请联系人加入群聊
    }
}
