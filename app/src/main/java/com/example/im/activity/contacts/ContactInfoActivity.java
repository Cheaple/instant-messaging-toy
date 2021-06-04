package com.example.im.activity.contacts;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.im.R;
import com.example.im.bean.chats.Message;

public class ContactInfoActivity extends AppCompatActivity {
    private static final int CONTACT_TYPE_LIST = 0x00001;  // 列表中的联系人
    private static final int CONTACT_TYPE_SEARCH = 0x00002;  // 搜索出的联系人

    private TextView nameTextView;
    private TextView idTextView;
    private ImageView avatarImageView;
    private LinearLayout chattingLayout;
    private LinearLayout addLayout;
    private LinearLayout deleteLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        Intent intent = getIntent();
        int type = intent.getIntExtra("Type", CONTACT_TYPE_LIST);
        int position = intent.getIntExtra("Position", 0);  // 被点击的联系人在联系人列表中的位置

        this.avatarImageView = this.findViewById(R.id.img_contact_info_avatar);
        this.nameTextView = this.findViewById(R.id.text_contact_info_nickname);
        this.idTextView = this.findViewById(R.id.text_contact_info_id);
        this.chattingLayout = this.findViewById(R.id.layout_go_chatting);
        this.addLayout = this.findViewById(R.id.layout_contact_add);
        this.deleteLayout = this.findViewById(R.id.layout_contact_delete);

        // 隐藏Add栏或Delete栏
        if (type == CONTACT_TYPE_LIST) {
            this.deleteLayout.setVisibility(View.VISIBLE);
            this.chattingLayout.setVisibility(View.VISIBLE);
            this.addLayout.setVisibility(View.GONE);

            // 点击事件：删除好友
            deleteLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: 删除好友
                    Toast.makeText(ContactInfoActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (type == CONTACT_TYPE_SEARCH) {
            this.addLayout.setVisibility(View.VISIBLE);
            this.deleteLayout.setVisibility(View.GONE);
            this.chattingLayout.setVisibility(View.GONE);

            // 点击事件：发送添加好友请求
            addLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: 发送添加好友请求
                    Toast.makeText(ContactInfoActivity.this, "Add", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // TODO: 展示联系人信息界面
        this.idTextView.setText("id: 66666");
    }
}
