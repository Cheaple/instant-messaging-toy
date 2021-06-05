package com.example.im.activity.chats;

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

import java.util.ArrayList;

public class GroupInfoActivity extends AppCompatActivity {
    private GroupMemberAdapter groupMemberAdapter;
    private RecyclerView recyclerView;
    private LinearLayout deleteLayout;
    private ArrayList<Contact> memberList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);
        Intent intent = getIntent();

        groupMemberAdapter = new GroupMemberAdapter(memberList, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        recyclerView = (RecyclerView)findViewById(R.id.recycle_view_group_members);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(groupMemberAdapter);

        deleteLayout = (LinearLayout)findViewById(R.id.layout_group_delete);

        // 设置点击事件
        deleteGroup();
    }

    private void deleteGroup() {
        deleteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GroupInfoActivity.this, "Delete and Leave", Toast.LENGTH_SHORT).show();
                // TODO：删除群聊
            }
        });
    }
}
