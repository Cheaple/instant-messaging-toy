package com.example.im.activity.chats;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.im.R;
import com.example.im.activity.contacts.ContactInfoActivity;
import com.example.im.adapter.chats.MessageAdapter;
import com.example.im.bean.chats.Message;

import java.util.LinkedList;

public class ChattingActivity extends AppCompatActivity {
    private static final int CHAT_TYPE_SINGLE = 0x00001;  // 对话
    private static final int CHAT_TYPE_GROUP = 0x00002;  // 群聊

    private MessageAdapter messageAdapter;
    private RecyclerView recyclerView;
    private EditText inputText;
    private Button sendButton;
    private LinkedList<Message> messageList = new LinkedList<Message>();

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        Intent intent = getIntent();
        int position = intent.getIntExtra("Position", 0);  // 被点击的会话在会话列表中的位置
        type = intent.getIntExtra("Chat Type", CHAT_TYPE_SINGLE);

        // TODO: 在messageList中导入历史消息
        messageList.add(new Message(1, "xixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixixi"));  // for test

        messageAdapter = new MessageAdapter(messageList, getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.msg_recycle_view);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        inputText = (EditText) findViewById(R.id.input_text);
        sendButton = (Button) findViewById(R.id.send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {  // 如果输入框非空，则发送消息
                    Message msg = new Message(0, content);
                    messageList.add(msg);
                    messageAdapter.notifyDataSetChanged();
                    //recyclerView.setSelection(messages.size());
                    inputText.setText("");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
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
}
