package com.example.im.mvp.presenter.chats;

import android.content.Context;

import com.example.im.bean.chats.Chat;
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
        this.mModel = new ChatsModel();
        this.mView = view;
    }

    @Override
    public void showChatList(){
        chatList = (LinkedList<Chat>) mModel.loadChatList();
        mView.setChatList(chatList);
    }

    @Override
    public Chat getChat(int position) {
        return chatList.get(position);
    }
}
