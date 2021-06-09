package com.example.im.activity.chats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.im.R;
import com.example.im.activity.contacts.ContactInfoActivity;
import com.example.im.adapter.chats.MessageAdapter;
import com.example.im.bean.chats.Message;
import com.example.im.bean.contacts.Contact;
import com.example.im.mvp.contract.IChattingContract;
import com.example.im.mvp.presenter.ChattingPresenter;

import java.util.LinkedList;
import java.util.List;

public class ChattingActivity extends AppCompatActivity implements IChattingContract.View, View.OnClickListener {
    private static final int CHAT_TYPE_SINGLE = 0x00001;  // 对话
    private static final int CHAT_TYPE_GROUP = 0x00002;  // 群聊

    private Context context;
    private ChattingPresenter mPresenter;

    private MessageAdapter messageAdapter;
    private RecyclerView recyclerView;
    private EditText inputText;
    private Button sendButton;

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        Intent intent = getIntent();
        //int position = intent.getIntExtra("Position", 0);  // 被点击的会话在会话列表中的位置
        type = intent.getIntExtra("Chat Type", CHAT_TYPE_SINGLE);

        context = getApplicationContext();
        mPresenter = new ChattingPresenter(this, context);

        recyclerView = (RecyclerView) findViewById(R.id.msg_recycle_view);
        inputText = (EditText) findViewById(R.id.input_text);
        sendButton = (Button) findViewById(R.id.send);
        sendButton.setOnClickListener(this);

        mPresenter.showMsgList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chatting_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 点击事件：查看联系人信息或群聊信息
        if (item.getItemId() == R.id.menu_info) {
            if (type == CHAT_TYPE_SINGLE) {
                Intent intent = new Intent(ChattingActivity.this, ContactInfoActivity.class);
                startActivityForResult(intent, 1);
            }
            else if (type == CHAT_TYPE_GROUP) {
                Intent intent = new Intent(ChattingActivity.this, GroupInfoActivity.class);
                startActivityForResult(intent, 1);
            }
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        // 点击事件：发送消息
        mPresenter.sendMsg();
    }

    @Override
    public void setMsgList(List list) {
        messageAdapter = new MessageAdapter((LinkedList<Message>) list, context);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void setMsgList() {
        messageAdapter.notifyDataSetChanged();
    }

    @Override
    public String getMsg() {
        return inputText.getText().toString();
    }

    @Override
    public void clearMsg() { inputText.setText(""); }
}
