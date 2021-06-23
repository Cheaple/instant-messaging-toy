package com.example.im.mvp.presenter.chats;

import android.content.Context;

import com.example.im.bean.AccountInfo;
import com.example.im.bean.chats.Chat;
import com.example.im.bean.chats.Msg;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.chats.IChattingContract;
import com.example.im.mvp.model.chats.ChattingModel;

import java.util.ArrayList;
import java.util.LinkedList;

public class ChattingPresenter implements IChattingContract.Presenter {
    private Context context;

    private IChattingContract.Model mModel;
    private IChattingContract.View mView;

    private String type;
    private String id;  // 群聊id
    private String contactUsername;  // 当会话为私人会话时，用来储存联系人用户名
    private LinkedList<Msg> msgList = new LinkedList<>();
    private ArrayList<Contact> memberList;
    private int msgType;


    public ChattingPresenter(IChattingContract.View view, Context context, String type, String id) {
        this.context = context;
        this.mModel = new ChattingModel(this);
        this.mView = view;
        this.type = type;
        this.id = id;
    }

    public ChattingPresenter(IChattingContract.View mView, Context context,
                             String type, String id, String contactUsername) {
        this.context = context;
        this.mModel = new ChattingModel(this);
        this.mModel = mModel;
        this.mView = mView;
        this.type = type;
        this.id = id;
        this.contactUsername = contactUsername;
    }

    public String  getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    @Override
    public void showMsgList(){
        mModel.loadMsgList(id);
    }

    public void loadSuccess(LinkedList<Msg> msgList) {
        this.msgList = msgList;
        mView.setMsgList(msgList, memberList);
    }

    @Override
    public void loadMemberInfo(ArrayList<String> members) {
        mModel.loadMemberInfo(members);
    }

    public void loadSuccess(ArrayList<Contact> memberList) {
        this.memberList = memberList;
        if (type.equals("PRIVATE_CHAT")) {
            if (!memberList.get(0).equals(AccountInfo.getInstance().getId()))
                contactUsername = memberList.get(1).getUsername();
            else
                contactUsername = memberList.get(0).getUsername();
        }  // 确定私人对话中会话对象的用户名
        showMsgList();  // 当群成员信息导入完毕后，再导入消息列表，以在每条消息前显示头像
    }

    @Override
    public void sendMsg() {
        String content = mView.getMsg();
        if (!"".equals(content)) {  // 如果输入框非空，则发送消息
            msgType = Msg.TYPE_MSG;
            Msg msg = new Msg(AccountInfo.getInstance().getId(), Msg.TYPE_MSG, content);
            msg.setLocal(true);
            msgList.add(msg);
            mModel.send(id, content, Msg.TYPE_MSG);
            mView.updateMsgList();
            mView.clearMsg();
        }
    }

    @Override
    public void sendPicture(String path) {
        msgType = Msg.TYPE_PICTURE;
        Msg msg = new Msg(AccountInfo.getInstance().getId(), Msg.TYPE_PICTURE, path);
        msg.setLocal(true);
        msgList.add(msg);
        mView.updateMsgList();
        mModel.upload("PICTURE", path);
    }

    @Override
    public void sendVideo(String path) {
        msgType = Msg.TYPE_VIDEO;
        Msg msg = new Msg(AccountInfo.getInstance().getId(), Msg.TYPE_VIDEO, path);
        msg.setLocal(true);
        msgList.add(msg);
        mView.updateMsgList();
        mModel.upload("VIDEO", path);
    }

    @Override
    public void sendLocation() {}

    public void sendSuccess() {
        showMsgList();  // 发送消息成功后，刷新列表
    }

    public void uploadSuccess(String file) {
        mModel.send(id, file, msgType);
    }


    @Override
    public void deleteMsg(int position) {
        mModel.deleteMsg(id, msgList.get(position).getId());
        msgList.remove(position);
        mView.updateMsgList();
    }

    public void deleteSuccess() {
        mView.showText("消息删除成功");
    }

    @Override
    public ArrayList<Contact> getMemberInfo() {
        return memberList;
    }

    @Override
    public void checkInfo() {
        mModel.checkInfo(contactUsername);
    }

    public void checkSuccess(Contact user, boolean isContact) {
        mView.gotoInfoActivity(user, isContact);
    }


    public void chattingFailure(String error) {
        mView.showText(error);
    }

}
