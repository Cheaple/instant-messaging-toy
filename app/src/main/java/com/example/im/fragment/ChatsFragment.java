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

import com.example.im.R;
import com.example.im.activity.chats.ChattingActivity;
import com.example.im.adapter.chats.ChatAdapter;
import com.example.im.bean.chats.Chat;


import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatsFragment extends Fragment {
    public static final int TEXT_REQUEST = 1;
    private static final int CHAT_TYPE_SINGLE = 0x00001;  // 对话
    private static final int CHAT_TYPE_GROUP = 0x00002;  // 群聊

    Context context;
    private ChatAdapter chatAdapter;
    private LinkedList<Chat> data;
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
        listView = getView().findViewById(R.id.listview);
        context = getActivity();

        // 向ListView 添加数据，新建ChatAdapter，并向listView绑定该Adapter
        data = new LinkedList<>();
        data.add(new Chat(getString(R.string.nickname1), R.drawable.avatar1, getString(R.string.sentence1), "2021/01/01", CHAT_TYPE_SINGLE));
        data.add(new Chat(getString(R.string.nickname2), R.drawable.avatar2, getString(R.string.sentence2), "2021/01/01", CHAT_TYPE_GROUP));

        chatAdapter = new ChatAdapter(data, context);
        listView.setAdapter(chatAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // 设置对话的点击事件：跳转至对话页面
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, ChattingActivity.class);
                startActivityForResult(intent, TEXT_REQUEST);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chats, container, false);
    }
}