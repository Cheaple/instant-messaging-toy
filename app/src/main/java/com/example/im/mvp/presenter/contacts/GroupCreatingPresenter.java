package com.example.im.mvp.presenter.contacts;

import android.content.Context;

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
    private int groupID;  // Used when inviting contacts

    public GroupCreatingPresenter(IGroupCreatingContract.View view, Context context) {
        this.context = context;
        this.mModel = new GroupCreatingModel(context);
        this.mView = view;
    }

    public GroupCreatingPresenter(IGroupCreatingContract.View view, Context context, int groupID) {
        this.context = context;
        this.mModel = new GroupCreatingModel(context);
        this.mView = view;
        this.groupID = groupID;
    }

    @Override
    public void showContactList() {
        contactList = (LinkedList<Contact>) mModel.loadContactList();
        mView.setContactList(contactList);
    }

    @Override
    public void createGroup() {
        mModel.createGroup(mView.getSelectedContacts());
        mView.gotoGroupChattingActivity();
    }

    @Override
    public void inviteContacts() {
        mModel.inviteContacts(groupID, mView.getSelectedContacts());
    }
}