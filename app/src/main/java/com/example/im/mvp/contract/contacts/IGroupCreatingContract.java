package com.example.im.mvp.contract.contacts;

import android.widget.Toast;

import com.example.im.bean.contacts.Contact;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public interface IGroupCreatingContract {
    interface View {
        void setContactList(List list);  // 初始化联系人列表
        ArrayList<String> getSelectedContacts();  // 获取被选中的联系人
        void gotoMainActivity();
        void gotoGroupChattingActivity(String groupID, ArrayList<String> members);
        void gotoGroupInfoActivity();
        void showText(String content);
    }
    interface Presenter {
        void showContactList();
        void createGroup();
        void inviteContacts();
    }
    interface Model {
        void loadContactList();
        void createGroup(ArrayList<String> selectedContacts);
        void inviteContacts(String groupID, ArrayList<String> selectedContacts);
    }
}
