package com.example.im.mvp.presenter.chats;

import com.example.im.R;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.chats.IGroupInfoContract;
import com.example.im.mvp.contract.contacts.IContactInfoContract;
import com.example.im.mvp.model.chats.GroupInfoModel;
import com.example.im.mvp.model.contacts.ContactInfoModel;

import java.util.LinkedList;
import java.util.List;

public class GroupInfoPresenter implements IGroupInfoContract.Presenter {
    IGroupInfoContract.Model mModel;
    IGroupInfoContract.View mView;

    private int id;
    private LinkedList<Contact> memberList;

    public GroupInfoPresenter(IGroupInfoContract.View view, int id) {
        this.mModel = new GroupInfoModel();
        this.mView = view;
        this.id = id;
    }

    @Override
    public void showMemberList() {
        memberList = (LinkedList<Contact>) mModel.loadMemberList();
        mView.setMemberList(memberList);
    }

    @Override
    public Contact getMember(int position) {
        return memberList.get(position);
    }

    @Override
    public void delete() {
        mModel.delete(id);
        mView.gotoMainActivity();
    }
}
