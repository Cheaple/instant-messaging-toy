package com.example.im.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.im.R;
import com.example.im.activity.chats.ChattingActivity;
import com.example.im.adapter.chats.ChatAdapter;
import com.example.im.bean.chats.Chat;

import com.example.im.mvp.contract.chats.IChatsContract;
import com.example.im.mvp.presenter.chats.ChatsPresenter;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatsFragment extends Fragment implements IChatsContract.View, AdapterView.OnItemClickListener {
    Context context;
    private ChatsPresenter mPresenter;

    private ChatAdapter chatAdapter;
    private ListView listView;

    public ChatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MessageFragment.
     */
    public static ChatsFragment newInstance() {
        ChatsFragment fragment = new ChatsFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        mPresenter = new ChatsPresenter(this, context);

        listView = getView().findViewById(R.id.listview);
        listView.setOnItemClickListener(this);

        mPresenter.showChatList();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chats, container, false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 点击事件：跳转至会话界面
        Intent intent = new Intent(context, ChattingActivity.class);
        intent.putExtra("Chat Type", mPresenter.getChat(position).getType());
        intent.putExtra("Chat ID", mPresenter.getChat(position).getId());
        startActivityForResult(intent, 1);
    }

    @Override
    public void setChatList(List list) {
        chatAdapter = new ChatAdapter((LinkedList<Chat>) list, context);
        listView.setAdapter(chatAdapter);
    }

    @Override
    public void setChatList() {
        chatAdapter.notifyDataSetChanged();
    }

    @Override
    public void showText(String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}