package com.example.im.mvp.presenter.chats;

import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.chats.IGroupInfoContract;
import com.example.im.mvp.model.chats.GroupInfoModel;

import java.util.ArrayList;
import java.util.LinkedList;

public class GroupInfoPresenter implements IGroupInfoContract.Presenter {
    IGroupInfoContract.Model mModel;
    IGroupInfoContract.View mView;

    private String groupID;
    private LinkedList<Contact> memberList;

    public GroupInfoPresenter(IGroupInfoContract.View view, String groupID, ArrayList<Contact> memberList) {
        this.mModel = new GroupInfoModel(this);
        this.mView = view;
        this.groupID = groupID;
        this.memberList = new LinkedList<>(memberList);
    }

    @Override
    public void showMemberList() {
        mView.setMemberList(memberList);
    }

    public void loadSuccess(LinkedList<Contact> memberList) {
        this.memberList = memberList;
        mView.setMemberList(memberList);
    }

    @Override
    public Contact getMember(int position) {
        return memberList.get(position);
    }

    public String getGroupID() {
        return groupID;
    }


    @Override
    public void delete() {
        mModel.delete(groupID);
        mView.gotoChattingActivity();
    }

    public void deleteSuccess() {
        mView.showText("退出群聊成功");
        mView.gotoMainActivity();
    }

    public void groupInfoFailure(String error) {
        mView.showText(error);
    }

}
