package com.example.im.activity.chats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.im.R;
import com.example.im.adapter.chats.GroupMemberAdapter;
import com.example.im.adapter.discover.ImageAdapter;
import com.example.im.bean.contacts.Contact;
import com.example.im.listener.OnItemClickListener;
import com.example.im.mvp.contract.chats.IGroupInfoContract;
import com.example.im.mvp.presenter.chats.GroupInfoPresenter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.OnItemClick;

public class GroupInfoActivity extends AppCompatActivity implements IGroupInfoContract.View, View.OnClickListener, OnItemClickListener {
    private Context context;
    private GroupInfoPresenter mPresenter;

    private GroupMemberAdapter groupMemberAdapter;
    private RecyclerView recyclerView;
    private LinearLayout deleteLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);
        Intent intent = getIntent();
        int id = intent.getIntExtra("Group ID", 0);

        context = getApplicationContext();
        mPresenter = new GroupInfoPresenter(this, id);

        recyclerView = (RecyclerView)findViewById(R.id.recycle_view_group_members);
        deleteLayout = (LinearLayout)findViewById(R.id.layout_group_delete);
        deleteLayout.setOnClickListener(this);

        mPresenter.showMemberList();
    }

    @Override
    public void onClick(View view) {
        // 点击事件：退出群聊
        Toast.makeText(GroupInfoActivity.this, "Delete and Leave", Toast.LENGTH_SHORT).show();
        mPresenter.delete();
    }

    @Override
    public void onItemClick(View view, int position) {
        if (position == groupMemberAdapter.getItemCount()) {
            // 点击事件：邀请联系人加入群聊

        }
        else {
            // 点击事件：查看群聊成员信息
            Toast.makeText(GroupInfoActivity.this, "Member Info", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setMemberList(List list) {
        groupMemberAdapter = new GroupMemberAdapter((LinkedList<Contact>) list, this);
        groupMemberAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setAdapter(groupMemberAdapter);
    }

    @Override
    public void updateMemberList() {
        groupMemberAdapter.notifyDataSetChanged();
    }

    @Override
    public void gotoMainActivity() {
        finish();
    }
}
