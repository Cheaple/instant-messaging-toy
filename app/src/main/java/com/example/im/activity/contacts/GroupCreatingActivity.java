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
import com.example.im.activity.discover.PostActivity;
import com.example.im.adapter.contacts.ContactAdapter;
import com.example.im.bean.contacts.Contact;
import com.example.im.listener.OnItemClickListener;
import com.example.im.mvp.contract.contacts.IGroupCreatingContract;
import com.example.im.mvp.presenter.contacts.GroupCreatingPresenter;

import java.util.LinkedList;
import java.util.List;

public class GroupCreatingActivity extends AppCompatActivity implements IGroupCreatingContract.View, OnItemClickListener, View.OnClickListener {
    private Context context;
    private GroupCreatingPresenter mPresenter;

    private ContactAdapter contactAdapter;
    private LinkedList<Contact> contacts;
    private RecyclerView recyclerView;
    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creating);
        Intent intent = getIntent();

        context = getApplicationContext();
        mPresenter = new GroupCreatingPresenter(this, context);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_contacts_selecting);
        createButton = (Button) findViewById(R.id.button_create);
        createButton.setOnClickListener(this);

        mPresenter.showContactList();
    }

    @Override
    public void onItemClick(View view, int position) {}

    @Override
    public void onClick(View view) {
        // 点击事件：创建群聊
        mPresenter.createGroup();
    }

    @Override
    public LinkedList<Contact> getSelectedContacts() {
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
    public void gotoGroupChattingActivity() {
        // TODO: 跳转至刚创建的群聊界面
        finish();
    }
}
