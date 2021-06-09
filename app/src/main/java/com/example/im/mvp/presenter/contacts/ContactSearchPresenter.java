package com.example.im.mvp.presenter.contacts;

import android.content.Context;

import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.contacts.IContactInfoContract;
import com.example.im.mvp.contract.contacts.IContactSearchContract;
import com.example.im.mvp.contract.contacts.IGroupCreatingContract;
import com.example.im.mvp.model.contacts.ContactSearchModel;
import com.example.im.mvp.model.contacts.GroupCreatingModel;

import java.util.LinkedList;

public class ContactSearchPresenter implements IContactSearchContract.Presenter {
    private Context context;

    IContactSearchContract.Model mModel;
    IContactSearchContract.View mView;

    private LinkedList<Contact> invitationList;

    public ContactSearchPresenter(IContactSearchContract.View view, Context context) {
        this.context = context;
        this.mModel = new ContactSearchModel();
        this.mView = view;
    }

    @Override
    public void showInvitationList() {
        invitationList = (LinkedList<Contact>) mModel.loadInvitationList();
        mView.setInvitationList(invitationList);
    }

    @Override
    public void searchUser() {
        Contact result = mModel.searchUser(mView.getTargetID());
        if (result != null) {

        }
        else {

        }
    }
}
