package com.example.im.mvp.presenter.chats;

import android.content.Context;

import com.example.im.bean.chats.Chat;
import com.example.im.bean.chats.Msg;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.chats.IChattingContract;
import com.example.im.mvp.model.chats.ChattingModel;

import java.util.LinkedList;

public class ChattingPresenter implements IChattingContract.Presenter {
    private Context context;

    private IChattingContract.Model mModel;
    private IChattingContract.View mView;

    private int type;
    private String id;  // 群聊id
    private String contactUsername;  // 当会话为私人会话时，用来储存联系人用户名
    private LinkedList<Msg> msgList;


    public ChattingPresenter(IChattingContract.View view, Context context, int type, String id) {
        this.context = context;
        this.mModel = new ChattingModel(this);
        this.mView = view;
        this.type = type;
        this.id = id;
    }

    public ChattingPresenter(IChattingContract.View mView, Context context,
                             int type, String id, String contactUsername) {
        this.context = context;
        this.mModel = new ChattingModel(this);
        this.mModel = mModel;
        this.mView = mView;
        this.type = type;
        this.id = id;
        this.contactUsername = contactUsername;
    }

    public int getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    @Override
    public void showMsgList(){
        System.out.println(id);
        mModel.loadMsgList(id);
    }

    public void loadSuccess(LinkedList<Msg> msgList) {
        this.msgList = msgList;
        mView.setMsgList(msgList);
    }

    @Override
    public void sendMsg() {
        String content = mView.getMsg();
        if (!"".equals(content)) {  // 如果输入框非空，则发送消息
            Msg msg = new Msg(0, Msg.TYPE_MSG, content);
            msgList.add(msg);
            mModel.sendMsg(id, content);
            mView.setMsgList();
            mView.clearMsg();
        }
    }

    @Override
    public void sendPicture(String path) {
        Msg msg = new Msg(0, Msg.TYPE_PICTURE, path);
        msgList.add(msg);
        mView.setMsgList();
        mModel.sendPicture(id, path);
    }

    @Override
    public void sendVideo(String path) {
        Msg msg = new Msg(0, Msg.TYPE_VIDEO, path);
        msgList.add(msg);
        mView.setMsgList();
        mModel.sendVideo(id, path);
    }

    @Override
    public void sendLocation() {

    }

    public void sendSuccess() {}

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
