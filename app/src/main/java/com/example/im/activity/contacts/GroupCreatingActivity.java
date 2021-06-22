package com.example.im.activity.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.im.R;
import com.example.im.activity.chats.ChattingActivity;
import com.example.im.activity.discover.PostActivity;
import com.example.im.adapter.contacts.ContactAdapter;
import com.example.im.bean.chats.Chat;
import com.example.im.bean.contacts.Contact;
import com.example.im.listener.OnItemClickListener;
import com.example.im.mvp.contract.contacts.IGroupCreatingContract;
import com.example.im.mvp.presenter.contacts.GroupCreatingPresenter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GroupCreatingActivity extends AppCompatActivity implements IGroupCreatingContract.View, OnItemClickListener, View.OnClickListener {
    public final static int TYPE_CREATE = 1;  // 用于新建群聊
    public final static int TYPE_INVITE = 1;  // 用于邀请联系人加入群聊

    private Context context;
    private GroupCreatingPresenter mPresenter;

    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;
    private Button createButton;

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creating);
        Intent intent = getIntent();
        type = intent.getIntExtra("Type", TYPE_CREATE);


        context = getApplicationContext();
        if (type == TYPE_CREATE)
            mPresenter = new GroupCreatingPresenter(this, context);
        else if (type == TYPE_INVITE)
            mPresenter = new GroupCreatingPresenter(this, context, intent.getIntExtra("Group ID", -1));

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_contacts_selecting);
        createButton = (Button) findViewById(R.id.button_create);
        createButton.setOnClickListener(this);

        mPresenter.showContactList();
    }

    @Override
    public void onItemClick(View view, int position) {}

    @Override
    public void onClick(View view) {
        if (type == TYPE_CREATE) {
            // 点击事件：创建群聊
            mPresenter.createGroup();
        }
        else if (type == TYPE_INVITE) {
            // 点击事件：邀请联系人加入群聊
            mPresenter.inviteContacts();
        }
    }

    @Override
    public ArrayList<String> getSelectedContacts() {
        return contactAdapter.getSelectedContacts();
    }

    @Override
    public void setContactList(List list) {
        contactAdapter = new ContactAdapter((LinkedList<Contact>) list, context, true);
        contactAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void gotoGroupChattingActivity(String groupID) {
        Intent intent = new Intent(context, ChattingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  // 结束当前activity
        intent.putExtra("Chat Type", Chat.CHAT_TYPE_GROUP);  // 传递会话类型
        intent.putExtra("Chat ID", groupID);  // 传递会话ID
        startActivity(intent);
        finish();
    }

    @Override
    public void showText(String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
