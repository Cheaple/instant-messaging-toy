package com.example.im.mvp.presenter.chats;

import android.content.Context;

import com.example.im.R;
import com.example.im.bean.AccountInfo;
import com.example.im.bean.chats.Chat;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.chats.IChatsContract;
import com.example.im.mvp.model.chats.ChatsModel;

import java.util.ArrayList;
import java.util.LinkedList;

public class ChatsPresenter implements IChatsContract.Presenter {
    private Context context;

    private IChatsContract.Model mModel;
    private IChatsContract.View mView;

    private LinkedList<Chat> chatList;
    private ArrayList<String> privateChatContactList = new ArrayList<>();  // 记录所有私人会话的会话对象

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

        // 选出所有私人会话的会话对象
        for (int i = 0; i < chatList.size(); ++i) {
            Chat chat = chatList.get(i);
            if (chat.getType().equals(Chat.CHAT_TYPE_SINGLE)) {
                String member1 = chat.getMemberIdList().get(0);  // 私人对话的两个联系人
                String member2 = chat.getMemberIdList().get(1);  // 必有一个为自己，另一个即是会话对象
                if (member1.equals(AccountInfo.getInstance().getId()))
                    privateChatContactList.add(member2);
                else
                    privateChatContactList.add(member1);
            }
        }
        mModel.loadContactInfo(privateChatContactList);  // 查询私人会话对象的详细信息
    }

    public void loadContactSuccess(LinkedList<Contact> contactList) {
        for (int i = 0; i < chatList.size(); ++i) {
            Chat chat = chatList.get(i);
            if (chat.getType().equals(Chat.CHAT_TYPE_SINGLE)) {
                for (int j = 0; j < contactList.size() ; ++j)
                    if (contactList.get(j).getId().equals(chat.getMemberIdList().get(0))
                            || contactList.get(j).getId().equals(chat.getMemberIdList().get(1)) ) {
                        String nickname = contactList.get(j).getNickname();
                        String avatar = contactList.get(j).getAvatar();
                        chatList.set(i, new Chat(chat, nickname, avatar));
                    }
            }
        }
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
