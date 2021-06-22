package com.example.im.mvp.presenter.contacts;

import android.content.Context;

import com.example.im.bean.AccountInfo;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.contacts.IContactsContract;
import com.example.im.mvp.contract.contacts.IGroupCreatingContract;
import com.example.im.mvp.model.contacts.ContactsModel;
import com.example.im.mvp.model.contacts.GroupCreatingModel;

import java.util.ArrayList;
import java.util.LinkedList;

public class GroupCreatingPresenter implements IGroupCreatingContract.Presenter {
    private Context context;

    IGroupCreatingContract.Model mModel;
    IGroupCreatingContract.View mView;

    private LinkedList<Contact> contactList;
    private String groupID;  // Used when inviting contacts
    ArrayList<String> members;  // // Used when inviting contacts

    public GroupCreatingPresenter(IGroupCreatingContract.View view, Context context) {
        this.context = context;
        this.mModel = new GroupCreatingModel(this);
        this.mView = view;
    }

    public GroupCreatingPresenter(IGroupCreatingContract.View view, Context context, String groupID) {
        this.context = context;
        this.mModel = new GroupCreatingModel(this);
        this.mView = view;
        this.groupID = groupID;
    }

    @Override
    public void showContactList() {
        mModel.loadContactList();
    }

    public void loadSuccess(LinkedList<Contact> contactList) {
        this.contactList = contactList;
        mView.setContactList(contactList);
    }
    public void loadFailure(String error) {
        mView.showText(error);
    }

    @Override
    public void createGroup() {
        members = mView.getSelectedContacts();
        members.add(AccountInfo.getInstance().getId());
        mModel.createGroup(members);
    }

    public void createSuccess(String groupID) {
        mView.gotoGroupChattingActivity(groupID, members);
    }
    public void createFailure(String error) {
        mView.showText(error);
    }


    @Override
    public void inviteContacts() {
        mModel.inviteContacts(groupID, mView.getSelectedContacts());
    }

    public void inviteSuccess() {
        mView.showText("邀请成功");
        mView.gotoMainActivity();
    }
    public void inviteFailure(String error) {
        mView.showText(error);
    }
}
