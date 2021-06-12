package com.example.im.mvp.presenter.contacts;

import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.contacts.IContactInfoContract;
import com.example.im.mvp.model.contacts.ContactInfoModel;

public class ContactInfoPresenter implements IContactInfoContract.Presenter {
    private IContactInfoContract.Model mModel;
    private IContactInfoContract.View mView;

    private Contact contact;

    public ContactInfoPresenter(IContactInfoContract.View view, Contact contact) {
        this.mModel = new ContactInfoModel();
        this.mView = view;
        this.contact = contact;
    }

    @Override
    public void showInfo() {
        mView.setAvatar(contact.getAvatarIcon());
        mView.setNickname(contact.getNickname());
        mView.setUsername(contact.getUsername());
        mView.setRegion(contact.getRegion());
    }

    @Override
    public void clearChattingHistory() {
        mModel.delete(contact.getID());
    }

    @Override
    public void add() {
        mModel.add(contact.getID());
        mView.gotoMainActivity();
    }
    @Override
    public void delete() {
        mModel.delete(contact.getID());
        mView.gotoMainActivity();
    }
}
