package com.example.im.mvp.presenter;

import android.content.Context;

import com.example.im.bean.chats.Message;
import com.example.im.mvp.contract.IChattingContract;
import com.example.im.mvp.model.ChattingModel;

import java.util.LinkedList;

public class ChattingPresenter implements IChattingContract.Presenter {
    private Context context;

    private IChattingContract.Model mModel;
    private IChattingContract.View mView;

    private LinkedList<Message> msgList;

    public ChattingPresenter(IChattingContract.View view, Context context) {
        this.context = context;
        this.mModel = new ChattingModel(context);
        this.mView = view;
    }

    @Override
    public void showMsgList(){
        msgList = (LinkedList<Message>) mModel.loadMsgList();
        mView.setMsgList(msgList);
    }

    @Override
    public void sendMsg() {
        String content = mView.getMsg();
        if (!"".equals(content)) {  // 如果输入框非空，则发送消息
            Message msg = new Message(0, content);
            msgList.add(msg);
            mModel.uploadMsg();
            mView.setMsgList();
            mView.clearMsg();
        }
    }
    }
