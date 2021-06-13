package com.example.im.mvp.presenter.contacts;

import android.content.Context;

import com.example.im.bean.AccountInfo;
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
        this.mModel = new ContactSearchModel(this);
        this.mView = view;
    }

    @Override
    public void showInvitationList() {}

    @Override
    public void searchUser() {
        String username = mView.getTargetUsername();
        if (!"".equals(username))
            mModel.searchUser(AccountInfo.getInstance().getUsername(), username);
    }

    public void searchSuccess(Contact contact) {
        mView.gotoContactInfoActivity(contact);
    }
    public void searchFailure(String error) {
        mView.showText(error);
    }

    @Override
    public void accept(int position) {
        Contact contact = invitationList.get(position);  // 从邀请列表中获取该联系人
        invitationList.remove(contact);  // 从邀请列表中移除该邀请
        mView.setInvitationList();  // 更新邀请列表
        mModel.accept(contact.getID());  // 接受好友邀请
    }

    @Override
    public void refuse(int position) {
        Contact contact = invitationList.get(position);  // 从邀请列表中获取该联系人
        invitationList.remove(contact);  // 从邀请列表中移除该邀请
        mView.setInvitationList();  // 更新邀请列表
        mModel.refuse(contact.getID());  // 拒绝好友邀请
    }
}
