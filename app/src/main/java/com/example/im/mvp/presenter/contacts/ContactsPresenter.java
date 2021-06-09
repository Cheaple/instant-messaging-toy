package com.example.im.mvp.presenter.contacts;

import android.content.Context;

import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.contacts.IContactsContract;
import com.example.im.mvp.model.contacts.ContactsModel;

import java.util.LinkedList;

public class ContactsPresenter implements IContactsContract.Presenter {
    private Context context;

    IContactsContract.Model mModel;
    IContactsContract.View mView;

    private LinkedList<Contact> contactList;

    public ContactsPresenter(IContactsContract.View view, Context context) {
        this.context = context;
        this.mModel = new ContactsModel(context);
        this.mView = view;
    }

    @Override
    public void showContactList(){
        contactList = (LinkedList<Contact>) mModel.loadContactList();
        mView.setContactList(contactList);
    }

    @Override
    public Contact getContact(int position) {
        return contactList.get(position);
    }
}
