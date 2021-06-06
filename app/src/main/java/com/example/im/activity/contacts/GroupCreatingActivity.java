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

import java.util.LinkedList;

public class GroupCreatingActivity extends AppCompatActivity {
    private Context context;
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
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_contacts_selecting);
        createButton = (Button) findViewById(R.id.button_create);

        // 添加数据，为recyclerView绑定Adapter、LayoutManager
        contacts = new LinkedList<Contact>();
        contacts.add(new Contact(getString(R.string.nickname1), R.drawable.avatar1));
        contacts.add(new Contact(getString(R.string.nickname2), R.drawable.avatar2));

        contactAdapter = new ContactAdapter(contacts, context, true);
        contactAdapter.setOnItemClickListener(new OnItemClickListener() {
            // 每个联系人的点击事件：跳转至联系人信息界面
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(getActivity(), "Position: " + position, 5000).show();
                Intent intent = new Intent(getApplicationContext(), ContactInfoActivity.class);
                intent.putExtra("Position", position);
                startActivityForResult(intent, 1);
            }
        });
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        // 设置点击事件
        createGroup();
    }

    private void createGroup() {
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO：创建群聊
                LinkedList<Contact> memberList = contactAdapter.getSelectedContacts();  // 获取用户勾选的联系人
            }
        });
    }
}
