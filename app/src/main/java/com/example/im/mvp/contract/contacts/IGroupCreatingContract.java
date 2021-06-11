package com.example.im.mvp.contract.contacts;

import com.example.im.bean.contacts.Contact;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public interface IGroupCreatingContract {
    interface View {
        void setContactList(List list);  // 初始化联系人列表
        ArrayList<String> getSelectedContacts();  // 获取被选中的联系人
        void gotoGroupChattingActivity();
    }
    interface Presenter {
        void showContactList();
        void createGroup();
        void inviteContacts();
    }
    interface Model {
        List loadContactList();
        void createGroup(ArrayList<String> selectedContacts);
        void inviteContacts(int groupID, ArrayList<String> selectedContacts);
    }
}
