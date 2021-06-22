package com.example.im.mvp.presenter.chats;

import android.content.Context;

import com.example.im.bean.chats.Chat;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.chats.IChatsContract;
import com.example.im.mvp.model.chats.ChatsModel;

import java.util.LinkedList;

public class ChatsPresenter implements IChatsContract.Presenter {
    private Context context;

    private IChatsContract.Model mModel;
    private IChatsContract.View mView;

    private LinkedList<Chat> chatList;

    public ChatsPresenter(IChatsContract.View view, Context context) {
        this.context = context;
        this.mModel = new ChatsModel(this);
        this.mView = view;
    }

    @Override
    public void showChatList(){
        mModel.loadChatList();
    }

    public void loadSuccess(LinkedList<Chat> chatList) {
        this.chatList = chatList;
        mView.setChatList(chatList);
    }
    public void loadFailure(String error) {
        mView.showText(error);
    }

    @Override
    public Chat getChat(int position) {
        return chatList.get(position);
    }
}
